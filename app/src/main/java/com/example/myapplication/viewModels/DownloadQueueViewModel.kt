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
import com.example.domain.usecase.dbToQueueImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@SuppressLint("Range")
@HiltViewModel
class DownloadQueueViewModel @Inject constructor(
    val dao: LogDao,
) : ViewModel() {

    private var _listOfDownloads : Flow<List<DownloadEntity>> = flow{}
    val listOfDownloads get() = _listOfDownloads

    init {
        //get downloads
        viewModelScope.launch(Dispatchers.IO) {
            _listOfDownloads = flow { dao.getQueueFilesFromStatus(false) }
        }
    }
}