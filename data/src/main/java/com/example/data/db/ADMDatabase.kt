package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.LogDao
import com.example.data.entity.DownloadLog

@Database(
    entities = arrayOf(DownloadLog::class),
    version = 1
)
abstract class ADMDatabase: RoomDatabase() {
    abstract fun logDao(): LogDao
}