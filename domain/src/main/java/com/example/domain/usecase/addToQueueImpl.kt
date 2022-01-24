package com.example.domain.usecase

import com.example.domain.model.DownloadLog
import com.example.domain.repository.downloadRepo
import javax.inject.Inject

class addToQueueImpl @Inject constructor(
    val downloadRepo: downloadRepo
): addToQueue {
    override suspend fun add(downloadLog: DownloadLog) {
        downloadRepo.addToDb(downloadLog)
    }
}