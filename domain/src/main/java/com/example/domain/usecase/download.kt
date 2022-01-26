package com.example.domain.usecase

import com.example.domain.model.DownloadLog

interface download {
    suspend fun download(downloadLog: DownloadLog): Long
}