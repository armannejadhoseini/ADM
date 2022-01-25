package com.example.myapplication.viewModels

import android.annotation.SuppressLint
import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dao.LogDao
import com.example.data.entity.DownloadEntity
import com.example.data.entity.DownloadProgress
import com.example.data.mapper.downloadEntityMapper
import com.example.data.mapper.downloadProgressMapper
import com.example.domain.usecase.dbToQueueImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@SuppressLint("Range")
@HiltViewModel
class DownloadQueueViewModel @Inject constructor(
    val downloadManager: DownloadManager,
    val dbToQueueImpl: dbToQueueImpl,
    val dao: LogDao,
    val downloadProgressMapper: downloadProgressMapper,
    val downloadEntityMapper: downloadEntityMapper
) : ViewModel() {

    private var _progress = MutableLiveData<Int>(0)
    val progress get() = _progress

    private var _listOfFiles = MutableLiveData<List<DownloadProgress>>()
    val listOfFiles get() = _listOfFiles

    private var listOfDownloadEntity = MutableLiveData<List<DownloadEntity>>()

    var isDownloading = false

    init {
        //get list of files in download queue
        getList()

        //check download status
        val cursor = downloadManager.query(DownloadManager.Query())
        if (cursor.moveToFirst()) {
            if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_RUNNING)
                isDownloading = true
        }

        //monitor download progress
        getProgress()
    }

    @SuppressLint("Range")
    fun getProgress() {

        //do while download is running
        while (isDownloading) {
            val cursor = downloadManager.query(DownloadManager.Query())
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
                            _progress.value = (downloadedBytes * 100 / totalBytes).toInt()
                        }
                    }

                    //if finished
                    (DownloadManager.STATUS_SUCCESSFUL) -> {
                        isDownloading = false
                        //call the usecase to add the download to queue
                        viewModelScope.launch(Dispatchers.IO) {
                            //map the data
                            val downloadEntity = downloadEntityMapper.entityToLog(listOfDownloadEntity.value!![0])
                            dbToQueueImpl.update(
                               downloadEntity
                            )
                        }
                    }

                }
            }
            cursor.close()
        }
    }

    //get download list
    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            //list of download entities
            listOfDownloadEntity.postValue(dao.getQueueFiles(false))
            //map it to a list of download progress
            if (!listOfDownloadEntity.value.isNullOrEmpty()) {
                _listOfFiles.postValue(downloadProgressMapper.EntityToProgress(listOfDownloadEntity.value!!))
            }
        }
    }
}