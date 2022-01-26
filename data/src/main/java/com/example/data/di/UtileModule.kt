package com.example.data.di

import android.app.DownloadManager
import android.content.Context
import com.example.domain.usecase.MonitorProgress
import com.example.domain.usecase.MonitorProgressImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtileModule {

    @Provides
    @Singleton
    fun ProvideDownloadManager(@ApplicationContext context: Context): DownloadManager {
        return context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }
}