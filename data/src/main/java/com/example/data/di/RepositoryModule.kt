package com.example.data.di

import com.example.data.repository.downloadRepoImpl
import com.example.domain.repository.downloadRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDownloadRepo(
        downloadRepoImpl: downloadRepoImpl
    ): downloadRepo
}