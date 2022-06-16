package com.example.jonas_hultn.di.module

import com.example.jonas_hultn.repository.service.BallonService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides


@Module
class ServiceModule {


    @Provides
    fun provideGson():Gson{
        return Gson()
    }

}