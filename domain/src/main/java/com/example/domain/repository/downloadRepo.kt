package com.example.domain.repository

import com.example.domain.model.DownloadLog

interface downloadRepo {
    suspend fun DownloadFile(downloadLog: DownloadLog): Long
    suspend fun addToDb(downloadLog: DownloadLog)
    suspend fun updateDb(downloadLog: DownloadLog)
}