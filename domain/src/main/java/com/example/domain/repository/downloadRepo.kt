package com.example.domain.repository

import com.example.domain.model.downloadFile

interface downloadRepo {
    suspend fun DownloadFile(downloadFile: downloadFile)
}