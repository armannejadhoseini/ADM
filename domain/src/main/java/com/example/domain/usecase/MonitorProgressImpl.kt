package com.example.domain.usecase

import android.annotation.SuppressLint
import android.app.DownloadManager
import javax.inject.Inject

class MonitorProgressImpl @Inject constructor(
    val downloadManager: DownloadManager
) : MonitorProgress {
    @SuppressLint("Range")
    override fun monitor(downloadId: Long): Int {
        var isDownloading = false
        //check download status
        val cursor = downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
        if (cursor.moveToFirst()) {
            if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_RUNNING)
                isDownloading = true
        }

        //do while download is running
        while (isDownloading) {
            val cursor = downloadManager.query(
                DownloadManager.Query().setFilterByStatus(DownloadManager.STATUS_RUNNING)
            )
            if (cursor.moveToFirst()) {
                //check if download is running or not
                when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    //if its running
                    (DownloadManager.STATUS_RUNNING) -> {
                        val totalBytes =
                            cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                        if (totalBytes > 0) {
                            val downloadedBytes =
                                cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                            val progress = (downloadedBytes * 100 / totalBytes).toInt()
                            return progress
                        }
                    }
                    //if finished
                    (DownloadManager.STATUS_SUCCESSFUL) -> {
                        isDownloading = false
                    }
                }
            }
            cursor.close()
        }
        return 0
    }
}
