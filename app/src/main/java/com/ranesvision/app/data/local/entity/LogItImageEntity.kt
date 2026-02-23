package com.ranesvision.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "logit_images",
    foreignKeys = [
        ForeignKey(
            entity = LogItEntryEntity::class,
            parentColumns = ["id"],
            childColumns = ["entryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["entryId"])]
)
data class LogItImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val entryId: Long,
    val imagePath: String,
    val comment: String = "",
    val sortOrder: Int = 0
)
