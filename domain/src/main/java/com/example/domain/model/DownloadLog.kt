package com.example.domain.model


data class DownloadLog(
    val id: Int,
    var fileName: String,
    var url: String,
    var time: String,
    var mimeType: String,
    var statusIsFinished: Boolean = false
)
