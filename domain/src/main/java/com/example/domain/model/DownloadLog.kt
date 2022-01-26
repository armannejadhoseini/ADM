package com.example.domain.model

data class DownloadLog(
    var fileName: String,
    var url: String,
    var time: String,
    var mimeType: String,
    var downloadId: Long,
    var statusIsFinished: Boolean = false
)