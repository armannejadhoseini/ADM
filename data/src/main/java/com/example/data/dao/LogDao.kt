package com.example.data.dao

import androidx.room.*
import com.example.data.entity.DownloadEntity
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM download WHERE statusIsFinished = :isFinished")
    fun getQueueFilesFromStatus(isFinished: Boolean): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM download WHERE downloadId = :downloadId")
    fun getQueueFilesFromDownloadId(downloadId: Long): DownloadEntity
}