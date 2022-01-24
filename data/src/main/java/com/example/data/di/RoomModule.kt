package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.dao.LogDao
import com.example.data.db.ADMDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Provides
    @Singleton
    fun ProvideRoom(@ApplicationContext context: Context): ADMDatabase {
        return Room.databaseBuilder(
            context,
            ADMDatabase::class.java,
            "adm_db"
        ).fallbackToDestructiveMigrationOnDowngrade().build()

    }

    @Provides
    @Singleton
    fun ProvideDao(db: ADMDatabase): LogDao {
        return db.logDao()
    }
}