package com.example.jonas_hultn.di.module

import com.apollographql.apollo3.ApolloClient
import com.example.jonas_hultn.repository.remote.RemoteDataSource
import com.example.jonas_hultn.ui.Main.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class PresenterModule {


    @Provides
    fun providerrepo(apolloClient: ApolloClient):RemoteDataSource{
        return RemoteDataSource(apolloClient)
    }

    @Provides
    fun providermainpresenter(remoteDataSource: RemoteDataSource):MainPresenter{
        return MainPresenter(remoteDataSource)
    }

//    @Provides
//    fun providerDetailPresenter(remoteDataSource: RemoteDataSource): DetailPresenter {
//        return DetailPresenter(remoteDataSource)
//    }

}