package com.ranesvision.app.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class LogItEntryWithImages(
    @Embedded val entry: LogItEntryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "entryId"
    )
    val images: List<LogItImageEntity>
)
