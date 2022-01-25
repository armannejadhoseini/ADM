package com.example.domain.model

data class downloadFile(
    var fileName: String,
    var url: String,
    var time: String,
    var mimeType: String,
    var progress: Int = 0
)
