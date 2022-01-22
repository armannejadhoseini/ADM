package com.example.myapplication.viewModels

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.downloadFile
import com.example.domain.usecase.downloadImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val downloadImpl: downloadImpl
): ViewModel() {
    private var _url = MutableLiveData<String>()
    val url get() = _url

    private var _fileName = MutableLiveData<String>()
    val fileName get() = _fileName

    private lateinit var time: String

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun download() {
        //get the current time
        time = Calendar.getInstance().time.toString()
        val downloadFile = downloadFile(fileName.value!!, url.value!!, time)

        //call the download usecase
        downloadImpl.download(downloadFile)

    }

    fun setFileName(): String {
        val uri = Uri.parse(url.value)
        _fileName.value = File(uri.path).name
        return fileName.value!!
    }

    fun setUrl(url: String) {
        _url.value = url
    }

}