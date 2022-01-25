package com.example.domain.usecase

import android.annotation.SuppressLint
import android.app.DownloadManager
import javax.inject.Inject

class MonitorProgressImpl @Inject constructor(): MonitorProgress {
    @SuppressLint("Range")
    override fun monitor(downloadManager: DownloadManager) {}

}