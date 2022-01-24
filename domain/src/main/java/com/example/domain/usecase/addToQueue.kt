package com.example.domain.usecase

import com.example.domain.model.DownloadLog

interface addToQueue {
    suspend fun add(downloadLog: DownloadLog)
}