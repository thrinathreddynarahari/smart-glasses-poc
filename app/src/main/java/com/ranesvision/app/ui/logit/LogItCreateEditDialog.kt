package com.ranesvision.app.ui.logit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ranesvision.app.ui.theme.*

@Composable
fun LogItCreateEditDialog(
    isEditing: Boolean,
    name: String,
    images: List<DialogImage>,
    onNameChange: (String) -> Unit,
    onAddFromCamera: () -> Unit,
    onAddFromAlbum: () -> Unit,
    onRemoveImage: (Int) -> Unit,
    onUpdateComment: (Int, String) -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SpaceBlack, DeepNavy)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isEditing) "Edit Entry" else "Create Entry",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextSecondary
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Entry name
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Entry Name", color = TextMuted) },
                placeholder = { Text("e.g. Site Inspection #12", color = TextMuted) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = NeonBlue,
                    focusedBorderColor = NeonBlue,
                    unfocusedBorderColor = GlassBorder,
                    focusedLabelColor = NeonBlue
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            // Add image buttons
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = onAddFromCamera,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = NeonBlue),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Take Photo")
                }
                OutlinedButton(
                    onClick = onAddFromAlbum,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = NeonBlue),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("From Album")
                }
            }

            Spacer(Modifier.height(16.dp))

            // Images list
            if (images.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No images added yet", color = TextMuted)
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                itemsIndexed(images) { index, image ->
                    ImageCommentCard(
                        image = image,
                        onRemove = { onRemoveImage(index) },
                        onCommentChange = { onUpdateComment(index, it) }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Save button
            Button(
                onClick = onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = if (isEditing) "Update" else "Save",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun ImageCommentCard(
    image: DialogImage,
    onRemove: () -> Unit,
    onCommentChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardSurface)
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box {
            AsyncImage(
                model = image.path,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = onRemove,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .background(Color.Black.copy(alpha = 0.6f), CircleShape)
            ) {
                Icon(
                    Icons.Default.RemoveCircle,
                    contentDescription = "Remove",
                    tint = Color.Red,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        OutlinedTextField(
            value = image.comment,
            onValueChange = onCommentChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Add a comment...", color = TextMuted) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = NeonBlue,
                focusedBorderColor = NeonBlue,
                unfocusedBorderColor = GlassBorder
            ),
            shape = RoundedCornerShape(8.dp),
            minLines = 2,
            maxLines = 4
        )
    }
}
