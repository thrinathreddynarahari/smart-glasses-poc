package com.ranesvision.app.ui.album

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranesvision.app.data.local.entity.ImageWithTags
import com.ranesvision.app.data.local.entity.TagEntity
import com.ranesvision.app.data.repository.AlbumRepository
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

data class AlbumUiState(
    val searchQuery: String = "",
    val showCamera: Boolean = false,
    val showTagSheet: Boolean = false,
    val selectedImageId: Long? = null,
    val message: String? = null
)

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: AlbumRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumUiState())
    val uiState: StateFlow<AlbumUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val images: StateFlow<List<ImageWithTags>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getAllImagesWithTags()
            } else {
                repository.searchImagesByTag(query)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allTags: StateFlow<List<TagEntity>> = repository.getAllTags()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun showCamera() {
        _uiState.update { it.copy(showCamera = true) }
    }

    fun hideCamera() {
        _uiState.update { it.copy(showCamera = false) }
    }

    fun onPhotoCaptured(filePath: String) {
        viewModelScope.launch {
            repository.saveImage(filePath)
            _uiState.update {
                it.copy(showCamera = false, message = "Photo saved successfully")
            }
        }
    }

    fun showTagSheet(imageId: Long) {
        _uiState.update { it.copy(showTagSheet = true, selectedImageId = imageId) }
    }

    fun hideTagSheet() {
        _uiState.update { it.copy(showTagSheet = false, selectedImageId = null) }
    }

    fun createTag(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.createTag(name.trim())
        }
    }

    fun assignTag(imageId: Long, tagId: Long) {
        viewModelScope.launch {
            repository.assignTagToImage(imageId, tagId)
        }
    }

    fun removeTag(imageId: Long, tagId: Long) {
        viewModelScope.launch {
            repository.removeTagFromImage(imageId, tagId)
        }
    }

    fun dismissMessage() {
        _uiState.update { it.copy(message = null) }
    }

    fun getImageStorageDir(): File {
        val dir = File(context.filesDir, "album")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }
}
