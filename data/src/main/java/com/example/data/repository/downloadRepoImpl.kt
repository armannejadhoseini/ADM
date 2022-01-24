package com.example.data.repository

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.net.toUri
import com.example.data.dao.LogDao
import com.example.data.db.ADMDatabase
import com.example.data.mapper.downloadEntityMapper
import com.example.domain.model.DownloadLog
import com.example.domain.model.downloadFile
import com.example.domain.repository.downloadRepo
import com.example.domain.usecase.addToQueueImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class downloadRepoImpl @Inject constructor(
    @ApplicationContext val context: Context,
    val dao: LogDao,
    val downloadEntityMapper: downloadEntityMapper
    ): downloadRepo {
    override suspend fun DownloadFile(downloadFile: downloadFile) {



        //get the download manager instance and make a request
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse(downloadFile.url)
        val request = DownloadManager.Request(uri)
        request.setTitle(downloadFile.fileName)
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        //location of the download
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!
        val file = File(dir.absolutePath + "/" + downloadFile.fileName)
        request.setDestinationUri(file.toUri())

        //start
        downloadManager.enqueue(request)



    }

    override suspend fun addToDb(downloadLog: DownloadLog) {
        //map data
        val downloadEntity = downloadEntityMapper.logToEntity(downloadLog)

        //add to database
        dao.insertLog(downloadEntity)
    }


}