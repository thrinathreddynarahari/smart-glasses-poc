package com.ranesvision.app.ui.logit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranesvision.app.data.local.entity.LogItEntryEntity
import com.ranesvision.app.data.local.entity.LogItEntryWithImages
import com.ranesvision.app.data.local.entity.LogItImageEntity
import com.ranesvision.app.data.repository.LogItRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

data class LogItUiState(
    val searchQuery: String = "",
    val showDialog: Boolean = false,
    val editingEntryId: Long? = null,
    val dialogName: String = "",
    val dialogImages: List<DialogImage> = emptyList(),
    val showCamera: Boolean = false,
    val showAlbumPicker: Boolean = false,
    val message: String? = null
)

data class DialogImage(
    val id: Long = 0,
    val path: String,
    val comment: String = "",
    val isNew: Boolean = true
)

@HiltViewModel
class LogItViewModel @Inject constructor(
    private val repository: LogItRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogItUiState())
    val uiState: StateFlow<LogItUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val entries: StateFlow<List<LogItEntryWithImages>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getAllEntries()
            } else {
                repository.searchEntries(query)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun showCreateDialog() {
        _uiState.update {
            it.copy(
                showDialog = true,
                editingEntryId = null,
                dialogName = "",
                dialogImages = emptyList()
            )
        }
    }

    fun showEditDialog(entryId: Long) {
        viewModelScope.launch {
            val entry = repository.getEntryById(entryId) ?: return@launch
            _uiState.update {
                it.copy(
                    showDialog = true,
                    editingEntryId = entryId,
                    dialogName = entry.entry.name,
                    dialogImages = entry.images.map { img ->
                        DialogImage(
                            id = img.id,
                            path = img.imagePath,
                            comment = img.comment,
                            isNew = false
                        )
                    }
                )
            }
        }
    }

    fun hideDialog() {
        _uiState.update {
            it.copy(
                showDialog = false,
                editingEntryId = null,
                dialogName = "",
                dialogImages = emptyList()
            )
        }
    }

    fun updateDialogName(name: String) {
        _uiState.update { it.copy(dialogName = name) }
    }

    fun addImageToDialog(imagePath: String) {
        _uiState.update {
            it.copy(
                dialogImages = it.dialogImages + DialogImage(path = imagePath),
                showCamera = false
            )
        }
    }

    fun addMultipleImagesToDialog(imagePaths: List<String>) {
        _uiState.update {
            it.copy(
                dialogImages = it.dialogImages + imagePaths.map { path -> DialogImage(path = path) },
                showAlbumPicker = false
            )
        }
    }

    fun removeImageFromDialog(index: Int) {
        _uiState.update {
            it.copy(dialogImages = it.dialogImages.toMutableList().apply { removeAt(index) })
        }
    }

    fun updateImageComment(index: Int, comment: String) {
        _uiState.update {
            val updated = it.dialogImages.toMutableList()
            updated[index] = updated[index].copy(comment = comment)
            it.copy(dialogImages = updated)
        }
    }

    fun showCamera() {
        _uiState.update { it.copy(showCamera = true) }
    }

    fun hideCamera() {
        _uiState.update { it.copy(showCamera = false) }
    }

    fun showAlbumPicker() {
        _uiState.update { it.copy(showAlbumPicker = true) }
    }

    fun hideAlbumPicker() {
        _uiState.update { it.copy(showAlbumPicker = false) }
    }

    fun saveEntry() {
        val state = _uiState.value
        if (state.dialogName.isBlank()) {
            _uiState.update { it.copy(message = "Please enter a name") }
            return
        }

        viewModelScope.launch {
            if (state.editingEntryId != null) {
                // Update existing entry
                val existing = repository.getEntryById(state.editingEntryId)
                if (existing != null) {
                    repository.updateEntry(existing.entry.copy(name = state.dialogName))
                    // Remove old images that are no longer present
                    val keepIds = state.dialogImages.filter { !it.isNew }.map { it.id }.toSet()
                    existing.images.forEach { img ->
                        if (img.id !in keepIds) {
                            repository.removeImage(img.id)
                        }
                    }
                    // Update existing images' comments
                    state.dialogImages.filter { !it.isNew }.forEach { dImg ->
                        val original = existing.images.find { it.id == dImg.id }
                        if (original != null && original.comment != dImg.comment) {
                            repository.updateImageComment(original.copy(comment = dImg.comment))
                        }
                    }
                    // Add new images
                    state.dialogImages.filter { it.isNew }.forEachIndexed { idx, dImg ->
                        repository.addImageToEntry(
                            entryId = state.editingEntryId,
                            imagePath = dImg.path,
                            comment = dImg.comment,
                            sortOrder = existing.images.size + idx
                        )
                    }
                }
                _uiState.update { it.copy(message = "Entry updated") }
            } else {
                // Create new entry
                val entryId = repository.createEntry(state.dialogName)
                state.dialogImages.forEachIndexed { idx, dImg ->
                    repository.addImageToEntry(
                        entryId = entryId,
                        imagePath = dImg.path,
                        comment = dImg.comment,
                        sortOrder = idx
                    )
                }
                _uiState.update { it.copy(message = "Entry created") }
            }
            hideDialog()
        }
    }

    fun deleteEntry(entry: LogItEntryEntity) {
        viewModelScope.launch {
            repository.deleteEntry(entry)
            _uiState.update { it.copy(message = "Entry deleted") }
        }
    }

    fun dismissMessage() {
        _uiState.update { it.copy(message = null) }
    }

    fun getImageStorageDir(): File {
        val dir = File(context.filesDir, "logit")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }
}
