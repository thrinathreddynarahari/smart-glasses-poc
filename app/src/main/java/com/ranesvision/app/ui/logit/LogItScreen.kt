package com.ranesvision.app.ui.logit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ranesvision.app.data.local.entity.LogItEntryWithImages
import com.ranesvision.app.ui.album.CameraScreen
import com.ranesvision.app.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LogItScreen(
    viewModel: LogItViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val entries by viewModel.entries.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissMessage()
        }
    }

    // Camera overlay (can be triggered from dialog)
    if (uiState.showCamera) {
        CameraScreen(
            storageDir = viewModel.getImageStorageDir(),
            onPhotoCaptured = { viewModel.addImageToDialog(it) },
            onClose = { viewModel.hideCamera() }
        )
        return
    }

    // Album picker overlay (triggered from dialog's "From Album" button)
    if (uiState.showAlbumPicker) {
        AlbumImagePicker(
            onImagesSelected = viewModel::addMultipleImagesToDialog,
            onDismiss = viewModel::hideAlbumPicker
        )
        return
    }

    // Create/Edit Dialog
    if (uiState.showDialog) {
        LogItCreateEditDialog(
            isEditing = uiState.editingEntryId != null,
            name = uiState.dialogName,
            images = uiState.dialogImages,
            onNameChange = viewModel::updateDialogName,
            onAddFromCamera = viewModel::showCamera,
            onAddFromAlbum = viewModel::showAlbumPicker,
            onRemoveImage = viewModel::removeImageFromDialog,
            onUpdateComment = viewModel::updateImageComment,
            onSave = viewModel::saveEntry,
            onDismiss = viewModel::hideDialog
        )
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SpaceBlack, DeepNavy)
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.showCreateDialog() },
                    containerColor = NeonBlue,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Create LOG-IT")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(12.dp))

                // Header
                Text(
                    text = "LOG-IT",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                // Search bar
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = viewModel::updateSearchQuery,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search entries...", color = TextMuted) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = TextMuted)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        cursorColor = NeonBlue,
                        focusedBorderColor = NeonBlue,
                        unfocusedBorderColor = GlassBorder
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))

                if (entries.isEmpty()) {
                    // Empty state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Default.Description,
                                contentDescription = null,
                                tint = TextMuted,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "No LOG-IT entries yet",
                                color = TextMuted,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Tap + to create your first entry",
                                color = TextMuted.copy(alpha = 0.6f),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 80.dp)
                    ) {
                        items(entries, key = { it.entry.id }) { entryWithImages ->
                            AnimatedVisibility(visible = true, enter = fadeIn()) {
                                LogItEntryCard(
                                    entryWithImages = entryWithImages,
                                    onClick = { viewModel.showEditDialog(entryWithImages.entry.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LogItEntryCard(
    entryWithImages: LogItEntryWithImages,
    onClick: () -> Unit
) {
    val entry = entryWithImages.entry
    val images = entryWithImages.images
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardSurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = dateFormat.format(Date(entry.timestamp)),
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMuted,
                    fontSize = 10.sp
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = "${images.size} image${if (images.size != 1) "s" else ""}",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )

            if (images.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(images.take(5)) { img ->
                        AsyncImage(
                            model = img.imagePath,
                            contentDescription = null,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    if (images.size > 5) {
                        item {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(GlassBorder),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+${images.size - 5}",
                                    color = TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
