package com.ranesvision.app.data.repository

import com.ranesvision.app.data.local.dao.ImageDao
import com.ranesvision.app.data.local.dao.TagDao
import com.ranesvision.app.data.local.entity.ImageEntity
import com.ranesvision.app.data.local.entity.ImageTagCrossRef
import com.ranesvision.app.data.local.entity.ImageWithTags
import com.ranesvision.app.data.local.entity.TagEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository @Inject constructor(
    private val imageDao: ImageDao,
    private val tagDao: TagDao
) {

    fun getAllImagesWithTags(): Flow<List<ImageWithTags>> {
        return imageDao.getAllImagesWithTags()
    }

    fun searchImagesByTag(query: String): Flow<List<ImageWithTags>> {
        return imageDao.searchImagesByTag(query)
    }

    suspend fun saveImage(filePath: String): Long {
        val entity = ImageEntity(filePath = filePath)
        return imageDao.insertImage(entity)
    }

    suspend fun deleteImage(imageId: Long) {
        imageDao.deleteImage(imageId)
    }

    // Tag operations
    fun getAllTags(): Flow<List<TagEntity>> {
        return tagDao.getAllTags()
    }

    fun getTagsForImage(imageId: Long): Flow<List<TagEntity>> {
        return tagDao.getTagsForImage(imageId)
    }

    suspend fun createTag(name: String): Long {
        val existing = tagDao.getTagByName(name)
        if (existing != null) return existing.id
        return tagDao.insertTag(TagEntity(name = name))
    }

    suspend fun assignTagToImage(imageId: Long, tagId: Long) {
        imageDao.insertImageTagCrossRef(ImageTagCrossRef(imageId, tagId))
    }

    suspend fun removeTagFromImage(imageId: Long, tagId: Long) {
        imageDao.removeImageTagCrossRef(imageId, tagId)
    }
}
