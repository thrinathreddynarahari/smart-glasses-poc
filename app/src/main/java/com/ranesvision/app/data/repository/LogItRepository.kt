package com.ranesvision.app.data.repository

import com.ranesvision.app.data.local.dao.LogItDao
import com.ranesvision.app.data.local.entity.LogItEntryEntity
import com.ranesvision.app.data.local.entity.LogItEntryWithImages
import com.ranesvision.app.data.local.entity.LogItImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogItRepository @Inject constructor(
    private val logItDao: LogItDao
) {

    fun getAllEntries(): Flow<List<LogItEntryWithImages>> =
        logItDao.getAllEntries()

    fun searchEntries(query: String): Flow<List<LogItEntryWithImages>> =
        logItDao.searchEntries(query)

    suspend fun getEntryById(entryId: Long): LogItEntryWithImages? =
        logItDao.getEntryById(entryId)

    suspend fun createEntry(name: String): Long {
        val entry = LogItEntryEntity(name = name)
        return logItDao.insertEntry(entry)
    }

    suspend fun updateEntry(entry: LogItEntryEntity) {
        logItDao.updateEntry(entry)
    }

    suspend fun deleteEntry(entry: LogItEntryEntity) {
        logItDao.deleteEntry(entry)
    }

    suspend fun addImageToEntry(entryId: Long, imagePath: String, comment: String = "", sortOrder: Int = 0): Long {
        val image = LogItImageEntity(
            entryId = entryId,
            imagePath = imagePath,
            comment = comment,
            sortOrder = sortOrder
        )
        return logItDao.insertImage(image)
    }

    suspend fun updateImageComment(image: LogItImageEntity) {
        logItDao.updateImage(image)
    }

    suspend fun removeImage(imageId: Long) {
        logItDao.deleteImageById(imageId)
    }
}
