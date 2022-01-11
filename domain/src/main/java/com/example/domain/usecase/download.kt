package com.example.domain.usecase

import com.example.domain.model.downloadFile

interface download {
    suspend fun download(downloadFile: downloadFile)
}