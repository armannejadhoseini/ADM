package com.example.myapplication.viewModels

import android.net.Uri
import android.os.Build
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.DownloadLog
import com.example.domain.model.downloadFile
import com.example.domain.usecase.addToQueueImpl
import com.example.domain.usecase.downloadImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.util.*
import javax.inject.Inject


@HiltViewModel
class LinkViewModel @Inject constructor(
    val downloadImpl: downloadImpl,
    val addToQueueImpl: addToQueueImpl
): ViewModel() {
    private var _url = MutableLiveData<String>()
    val url get() = _url

    private var _fileName = MutableLiveData<String>()
    val fileName get() = _fileName

    private var _mimeType = MutableLiveData<String>()
    val mimeType get() = _mimeType

    private lateinit var time: String

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun download() {
        //get the current time
        time = Calendar.getInstance().time.toString()
        val downloadFile = downloadFile(fileName.value!!, url.value!!, time, mimeType.value!!)

        //call the usecase to add the download to queue
        addToQueueImpl.add(
            //make a log
            DownloadLog(
                0,
                downloadFile.fileName,
                downloadFile.url,
                downloadFile.time,
                downloadFile.mimeType
            )
        )

        //call the download usecase
        downloadImpl.download(downloadFile)

    }

    //setters
    fun setFileName(): String {
        val uri = Uri.parse(url.value)
        _fileName.value = File(uri.path!!).name
        return fileName.value!!
    }

    fun setUrl(url: String) {
        _url.value = url
    }

    fun setMimeType(url: String) {
        _mimeType.value = MimeTypeMap.getFileExtensionFromUrl(url)
    }
}