package com.example.myapplication.viewModels

import android.net.Uri
import android.os.Build
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DownloadLog
import com.example.domain.usecase.dbToQueueImpl
import com.example.domain.usecase.downloadImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import javax.inject.Inject


@HiltViewModel
class LinkViewModel @Inject constructor(
    val downloadImpl: downloadImpl,
    val dbToQueueImpl: dbToQueueImpl
) : ViewModel() {

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
        //make a log
        val downloadLog = DownloadLog(fileName.value!!, url.value!!, time, mimeType.value!!, 0, false)

        //call the download usecase
        val downloadId = downloadImpl.download(downloadLog)

        //update download log with new downloadId
        downloadLog.downloadId = downloadId

        //call the usecase to add the download to queue
        viewModelScope.launch(Dispatchers.IO) {
            dbToQueueImpl.add(downloadLog)
        }
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