package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download")
data class DownloadEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var fileName: String,
    var url: String,
    var time: String,
    var mimeType: String,
    var progress: Int,
    var statusIsFinished: Boolean = false
        )