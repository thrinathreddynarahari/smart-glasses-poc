package com.ranesvision.app.ui.logit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ranesvision.app.ui.album.AlbumViewModel
import com.ranesvision.app.ui.theme.*

/**
 * Full-screen overlay that shows the Album grid and lets the user
 * select one or more existing images to add to a LOG-IT entry.
 */
@Composable
fun AlbumImagePicker(
    onImagesSelected: (List<String>) -> Unit,
    onDismiss: () -> Unit,
    albumViewModel: AlbumViewModel = hiltViewModel()
) {
    val images by albumViewModel.images.collectAsState()
    val selectedPaths = remember { mutableStateListOf<String>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SpaceBlack, DeepNavy)
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Header
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Select Images",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                IconButton(onClick = onDismiss, modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary)
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "${selectedPaths.size} selected",
                color = TextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(12.dp))

            if (images.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No images in Album", color = TextMuted)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(images, key = { it.image.id }) { imageWithTags ->
                        val path = imageWithTags.image.filePath
                        val isSelected = path in selectedPaths

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    if (isSelected) selectedPaths.remove(path)
                                    else selectedPaths.add(path)
                                }
                                .then(
                                    if (isSelected) Modifier.border(3.dp, NeonBlue, RoundedCornerShape(8.dp))
                                    else Modifier
                                )
                        ) {
                            AsyncImage(
                                model = path,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                contentScale = ContentScale.Crop
                            )
                            if (isSelected) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = NeonBlue,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(4.dp)
                                        .size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Confirm button
            if (selectedPaths.isNotEmpty()) {
                Button(
                    onClick = { onImagesSelected(selectedPaths.toList()) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Add ${selectedPaths.size} Image${if (selectedPaths.size > 1) "s" else ""}",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
