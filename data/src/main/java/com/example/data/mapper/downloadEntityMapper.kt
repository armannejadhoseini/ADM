package com.example.data.mapper

import com.example.data.entity.DownloadEntity
import com.example.domain.model.DownloadLog
import javax.inject.Inject

class downloadEntityMapper @Inject constructor (
) {
    fun logToEntity(downloadLog: DownloadLog): DownloadEntity {
        return downloadLog.let {
            DownloadEntity(
                0,
                it.fileName,
                it.url,
                it.time,
                it.mimeType,
                it.downloadId
            )
        }
    }

    fun entityToLog(downloadEntity: DownloadEntity): DownloadLog {
        return downloadEntity.let {
            DownloadLog(
                it.fileName,
                it.url,
                it.time,
                it.mimeType,
                it.downloadId,
                true
            )
        }
    }
}