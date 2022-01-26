package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.LogDao
import com.example.data.entity.DownloadEntity

@Database(
    entities = [DownloadEntity::class],
    version = 4
)
abstract class ADMDatabase: RoomDatabase() {
    abstract fun logDao(): LogDao
}