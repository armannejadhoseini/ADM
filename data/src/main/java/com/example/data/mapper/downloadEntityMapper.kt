package com.example.data.mapper

import com.example.data.entity.DownloadEntity
import com.example.domain.model.DownloadLog
import com.example.domain.model.downloadFile
import dagger.hilt.EntryPoint
import javax.inject.Inject

@EntryPoint
class downloadEntityMapper @Inject constructor (

) {
    fun logToEntity(downloadLog: DownloadLog): DownloadEntity {
        return downloadLog.let {
            DownloadEntity(
                0,
                it.fileName,
                it.url,
                it.time,
                it.mimeType
            )
        }
    }
}