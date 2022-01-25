package com.example.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dao.LogDao
import com.example.data.entity.DownloadEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FinishedViewModel @Inject constructor(
    private val dao: LogDao
): ViewModel() {

    private var _log = MutableLiveData<List<DownloadEntity>>()
    val log get() = _log

    init {
        //get logs from db
        viewModelScope.launch(Dispatchers.IO) {
            _log.postValue(dao.getQueueFiles(true))
        }
    }

}