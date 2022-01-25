package com.example.domain.usecase

import com.example.domain.model.DownloadLog
import com.example.domain.repository.downloadRepo
import javax.inject.Inject

class dbToQueueImpl @Inject constructor(
    val downloadRepo: downloadRepo
): dbToQueue {
    override suspend fun add(downloadLog: DownloadLog) {
        downloadRepo.addToDb(downloadLog)
    }

    override suspend fun update(downloadLog: DownloadLog) {
        downloadRepo.updateDb(downloadLog)
    }
}