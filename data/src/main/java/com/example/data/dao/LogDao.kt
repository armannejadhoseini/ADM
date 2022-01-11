package com.example.data.dao

import androidx.room.*
import com.example.data.entity.DownloadLog

@Dao
interface LogDao {
    @Insert
    fun insertLog(log: DownloadLog)

    @Delete
    fun deleteLog(log: DownloadLog)

    @Update()
    fun updateLog(log: DownloadLog)

    @Query("SELECT * FROM download")
    fun getLogs(): List<DownloadLog>
}