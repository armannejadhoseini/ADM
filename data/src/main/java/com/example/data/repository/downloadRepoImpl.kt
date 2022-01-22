package com.example.data.repository

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import com.example.domain.model.downloadFile
import com.example.domain.repository.downloadRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class downloadRepoImpl @Inject constructor(
    @ApplicationContext val context: Context,
): downloadRepo {
    override suspend fun DownloadFile(downloadFile: downloadFile) {

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse(downloadFile.url)
        val request = DownloadManager.Request(uri)
        request.setTitle(downloadFile.fileName)
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationUri(Uri.parse("file://Download/" + downloadFile.fileName))
        downloadManager.enqueue(request)
    }
}