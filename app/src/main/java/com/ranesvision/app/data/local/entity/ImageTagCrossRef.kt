package com.ranesvision.app.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "image_tag_cross_ref",
    primaryKeys = ["imageId", "tagId"]
)
data class ImageTagCrossRef(
    val imageId: Long,
    val tagId: Long
)
