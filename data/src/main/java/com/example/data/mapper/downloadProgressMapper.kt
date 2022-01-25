package com.example.data.mapper

import com.example.data.entity.DownloadEntity
import com.example.data.entity.DownloadProgress
import javax.inject.Inject

class downloadProgressMapper @Inject constructor(
) {
    fun EntityToProgress(entity: List<DownloadEntity>): List<DownloadProgress> {
        return entity.map {
            DownloadProgress(
                it.fileName
            )
        }
    }
}