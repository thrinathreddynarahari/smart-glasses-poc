package com.ranesvision.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val filePath: String,
    val thumbnailPath: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val width: Int = 0,
    val height: Int = 0
)
