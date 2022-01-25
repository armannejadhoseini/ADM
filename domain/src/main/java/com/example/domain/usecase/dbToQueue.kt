package com.example.domain.usecase

import com.example.domain.model.DownloadLog

interface dbToQueue {
    suspend fun add(downloadLog: DownloadLog)
    suspend fun update(downloadLog: DownloadLog)
}