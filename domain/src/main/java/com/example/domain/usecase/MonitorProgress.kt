package com.example.domain.usecase

import android.app.DownloadManager

interface MonitorProgress {
    fun monitor(downloadId: Long): Int
}