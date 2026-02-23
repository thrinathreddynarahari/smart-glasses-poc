package com.ranesvision.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ranesvision.app.data.local.dao.ImageDao
import com.ranesvision.app.data.local.dao.LogItDao
import com.ranesvision.app.data.local.dao.TagDao
import com.ranesvision.app.data.local.entity.ImageEntity
import com.ranesvision.app.data.local.entity.ImageTagCrossRef
import com.ranesvision.app.data.local.entity.LogItEntryEntity
import com.ranesvision.app.data.local.entity.LogItImageEntity
import com.ranesvision.app.data.local.entity.TagEntity

@Database(
    entities = [
        ImageEntity::class,
        TagEntity::class,
        ImageTagCrossRef::class,
        LogItEntryEntity::class,
        LogItImageEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun tagDao(): TagDao
    abstract fun logItDao(): LogItDao
}
