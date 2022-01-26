package com.example.data.repository

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.net.toUri
import com.example.data.dao.LogDao
import com.example.data.mapper.downloadEntityMapper
import com.example.domain.model.DownloadLog
import com.example.domain.repository.downloadRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import java.io.File


class downloadRepoImpl @Inject constructor(
    @ApplicationContext val context: Context,
    val dao: LogDao,
    val downloadEntityMapper: downloadEntityMapper,
    val downloadManager: DownloadManager
) : downloadRepo {
    @SuppressLint("Range")
    override suspend fun DownloadFile(downloadLog: DownloadLog): Long {

        //get the download manager instance and make a request
        val uri: Uri = Uri.parse(downloadLog.url)
        val request = DownloadManager.Request(uri)
        request.setTitle(downloadLog.fileName)
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        //location of the download
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!
        val file = File(dir.absolutePath + "/" + downloadLog.fileName)
        request.setDestinationUri(file.toUri())

        //start
        val downloadId = downloadManager.enqueue(request)
        return downloadId
    }


    override suspend fun addToDb(downloadLog: DownloadLog) {
        //map data
        val downloadEntity = downloadEntityMapper.logToEntity(downloadLog)

        //add to database
        dao.insertLog(downloadEntity)
    }

    override suspend fun updateDb(downloadLog: DownloadLog) {
        //map data
        val downloadEntity = downloadEntityMapper.logToEntity(downloadLog)

        //add to database
        dao.updateLog(downloadEntity)
    }
}