package com.ranesvision.app.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ImageWithTags(
    @Embedded val image: ImageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ImageTagCrossRef::class,
            parentColumn = "imageId",
            entityColumn = "tagId"
        )
    )
    val tags: List<TagEntity>
)
