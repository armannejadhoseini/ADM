package com.example.domain.repository

import com.example.domain.model.DownloadLog
import com.example.domain.model.downloadFile

interface downloadRepo {
    suspend fun DownloadFile(downloadFile: downloadFile)
    suspend fun addToDb(downloadLog: DownloadLog)
}