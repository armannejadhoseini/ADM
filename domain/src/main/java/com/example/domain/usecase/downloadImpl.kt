package com.example.domain.usecase

import android.content.Context
import com.example.domain.model.downloadFile
import com.example.domain.repository.downloadRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class downloadImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val downloadRepo: downloadRepo
) : download {
    override suspend fun download(downloadFile: downloadFile) {
        downloadRepo.DownloadFile(downloadFile)
    }
}