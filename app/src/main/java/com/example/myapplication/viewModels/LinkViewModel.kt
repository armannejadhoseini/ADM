package com.example.myapplication.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.downloadFile
import com.example.domain.usecase.downloadImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(
    val downloadImpl: downloadImpl
): ViewModel() {
    private var _url = MutableLiveData<String>()
    val url get() = _url

    private var _fileName = MutableLiveData<String>()
    val fileName get() = _fileName

    private lateinit var time: String

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun download() {
        Log.d("TAG", ": started")

        time = Calendar.getInstance().time.toString()
        val downloadFile = downloadFile(fileName.value!!, url.value!!, time)
        downloadImpl.download(downloadFile)
    }

    fun setFileName(fileName: String) {
        _fileName.value = fileName
    }

    fun setUrl(url: String) {
        _url.value = url
    }

}