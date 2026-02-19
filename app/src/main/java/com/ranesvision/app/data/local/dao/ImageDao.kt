package com.ranesvision.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ranesvision.app.data.local.entity.ImageEntity
import com.ranesvision.app.data.local.entity.ImageTagCrossRef
import com.ranesvision.app.data.local.entity.ImageWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: ImageEntity): Long

    @Query("SELECT * FROM images ORDER BY timestamp DESC")
    fun getAllImages(): Flow<List<ImageEntity>>

    @Transaction
    @Query("SELECT * FROM images ORDER BY timestamp DESC")
    fun getAllImagesWithTags(): Flow<List<ImageWithTags>>

    @Transaction
    @Query("""
        SELECT DISTINCT images.* FROM images
        INNER JOIN image_tag_cross_ref ON images.id = image_tag_cross_ref.imageId
        INNER JOIN tags ON image_tag_cross_ref.tagId = tags.id
        WHERE LOWER(tags.name) LIKE '%' || LOWER(:query) || '%'
        ORDER BY images.timestamp DESC
    """)
    fun searchImagesByTag(query: String): Flow<List<ImageWithTags>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImageTagCrossRef(crossRef: ImageTagCrossRef)

    @Query("DELETE FROM image_tag_cross_ref WHERE imageId = :imageId AND tagId = :tagId")
    suspend fun removeImageTagCrossRef(imageId: Long, tagId: Long)

    @Query("DELETE FROM images WHERE id = :imageId")
    suspend fun deleteImage(imageId: Long)
}
