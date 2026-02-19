package com.ranesvision.app.ui.album

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ranesvision.app.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val images by viewModel.images.collectAsState()
    val allTags by viewModel.allTags.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissMessage()
        }
    }

    // Camera overlay
    if (uiState.showCamera) {
        CameraScreen(
            storageDir = viewModel.getImageStorageDir(),
            onPhotoCaptured = { viewModel.onPhotoCaptured(it) },
            onClose = { viewModel.hideCamera() }
        )
        return
    }

    // Tag bottom sheet
    if (uiState.showTagSheet && uiState.selectedImageId != null) {
        val imageId = uiState.selectedImageId!!
        val selectedImage = images.find { it.image.id == imageId }
        val assignedTagIds = selectedImage?.tags?.map { it.id } ?: emptyList()

        TagBottomSheet(
            allTags = allTags,
            assignedTagIds = assignedTagIds,
            onCreateTag = { viewModel.createTag(it) },
            onAssignTag = { viewModel.assignTag(imageId, it) },
            onRemoveTag = { viewModel.removeTag(imageId, it) },
            onDismiss = { viewModel.hideTagSheet() }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SpaceBlack, DeepNavy, SpaceBlack)
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Album",
                style = MaterialTheme.typography.headlineLarge,
                color = TextPrimary,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search bar
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = {
                    Text("Search by tag", color = TextMuted)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = TextMuted
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonBlue,
                    unfocusedBorderColor = GlassBorder,
                    focusedContainerColor = CardSurface.copy(alpha = 0.5f),
                    unfocusedContainerColor = CardSurface.copy(alpha = 0.3f),
                    cursorColor = NeonBlue,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (images.isEmpty()) {
                // Empty state
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Image,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = TextMuted.copy(alpha = 0.4f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No images yet",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextMuted,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Capture photos to see them here",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextMuted.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                // Image grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(images) { index, imageWithTags ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(tween(300 + index * 50))
                        ) {
                            ImageGridItem(
                                imageWithTags = imageWithTags,
                                onTagClick = { viewModel.showTagSheet(imageWithTags.image.id) }
                            )
                        }
                    }
                }
            }
        }

        // Capture FAB
        FloatingActionButton(
            onClick = { viewModel.showCamera() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 100.dp),
            containerColor = NeonBlue,
            contentColor = SpaceBlack,
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Capture")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Capture",
                    style = MaterialTheme.typography.labelLarge,
                    color = SpaceBlack
                )
            }
        }

        // Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = NeonBlueDark,
                    contentColor = Color.White
                )
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageGridItem(
    imageWithTags: com.ranesvision.app.data.local.entity.ImageWithTags,
    onTagClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CardSurface)
            .border(1.dp, GlassBorder, RoundedCornerShape(12.dp))
    ) {
        // Image
        AsyncImage(
            model = imageWithTags.image.filePath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop
        )

        // Tags row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onTagClick)
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.LocalOffer,
                contentDescription = "Tags",
                modifier = Modifier.size(14.dp),
                tint = NeonBlue.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            if (imageWithTags.tags.isEmpty()) {
                Text(
                    text = "Add tags",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted,
                    maxLines = 1
                )
            } else {
                Text(
                    text = imageWithTags.tags.joinToString(", ") { it.name },
                    style = MaterialTheme.typography.bodySmall,
                    color = NeonBlueBright,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
