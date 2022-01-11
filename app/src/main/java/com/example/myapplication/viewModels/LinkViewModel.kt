package com.example.myapplication.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(): ViewModel() {
    private var _url = MutableLiveData<String>()
    val url get() = _url

    private var _fileName = MutableLiveData<String>()
    val fileName get() = _fileName

    private lateinit var time: String

    fun download() {
        Log.d("TAG", ": started")
    }

}