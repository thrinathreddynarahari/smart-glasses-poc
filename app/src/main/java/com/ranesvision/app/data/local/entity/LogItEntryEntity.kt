package com.ranesvision.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logit_entries")
data class LogItEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val timestamp: Long = System.currentTimeMillis()
)
