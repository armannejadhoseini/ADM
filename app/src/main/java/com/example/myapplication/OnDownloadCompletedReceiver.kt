package com.example.myapplication

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.data.dao.LogDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnDownloadCompletedReceiver : BroadcastReceiver() {

    @Inject lateinit var dao: LogDao

    override fun onReceive(context: Context?, intent: Intent?) {
        //getting the download id
        val downloadId = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

        GlobalScope.launch(Dispatchers.IO) {

            //get the entity to update it
            val downloadEntity = dao.getQueueFilesFromDownloadId(downloadId!!)
            //change the status to finished
            downloadEntity.statusIsFinished = true

            //update the entity
            dao.updateLog(downloadEntity)

        }

        Toast.makeText(context, "Complete download", Toast.LENGTH_LONG).show()
    }
}