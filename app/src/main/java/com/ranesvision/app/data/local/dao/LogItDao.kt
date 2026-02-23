package com.ranesvision.app.data.local.dao

import androidx.room.*
import com.ranesvision.app.data.local.entity.LogItEntryEntity
import com.ranesvision.app.data.local.entity.LogItEntryWithImages
import com.ranesvision.app.data.local.entity.LogItImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LogItDao {

    @Transaction
    @Query("SELECT * FROM logit_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<LogItEntryWithImages>>

    @Transaction
    @Query("SELECT * FROM logit_entries WHERE name LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchEntries(query: String): Flow<List<LogItEntryWithImages>>

    @Transaction
    @Query("SELECT * FROM logit_entries WHERE id = :entryId")
    suspend fun getEntryById(entryId: Long): LogItEntryWithImages?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: LogItEntryEntity): Long

    @Update
    suspend fun updateEntry(entry: LogItEntryEntity)

    @Delete
    suspend fun deleteEntry(entry: LogItEntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: LogItImageEntity): Long

    @Update
    suspend fun updateImage(image: LogItImageEntity)

    @Delete
    suspend fun deleteImage(image: LogItImageEntity)

    @Query("DELETE FROM logit_images WHERE id = :imageId")
    suspend fun deleteImageById(imageId: Long)
}
