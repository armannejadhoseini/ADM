package com.example.data.dao

import androidx.room.*
import com.example.data.entity.DownloadEntity

@Dao
interface LogDao {
    @Insert
    fun insertLog(entity: DownloadEntity)

    @Delete
    fun deleteLog(entity: DownloadEntity)

    @Update()
    fun updateLog(entity: DownloadEntity)

    @Query("SELECT * FROM download")
    fun getLogs(): List<DownloadEntity>

    @Query("SELECT * FROM download WHERE statusIsFinished = (:isFinished)")
    fun getQueueFiles(isFinished: Boolean): List<DownloadEntity>
}