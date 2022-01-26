package com.example.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dao.LogDao
import com.example.data.entity.DownloadEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FinishedViewModel @Inject constructor(
    private val dao: LogDao
): ViewModel() {

    private var _log : Flow<List<DownloadEntity>> = flow {}
    val log get() = _log

    init {
        //get logs from db
        viewModelScope.launch(Dispatchers.IO) {
            _log = dao.getQueueFilesFromStatus(true)
            Log.d("TAG", "files: ${log}")
        }
    }

}