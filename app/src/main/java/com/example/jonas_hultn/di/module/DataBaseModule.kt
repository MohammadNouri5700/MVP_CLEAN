package com.example.jonas_hultn.di.module

import android.content.Context
import com.example.jonas_hultn.factory.DaggerMainApplication
import com.example.jonas_hultn.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {




    @Singleton
    @Provides
    fun provideDatabase() = AppDatabase.getDatabase(DaggerMainApplication.context)


    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.createMessageDao()
}