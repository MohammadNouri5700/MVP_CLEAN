package com.example.jonas_hultn.di.module

import com.apollographql.apollo3.ApolloClient
import com.example.jonas_hultn.factory.Constant
import dagger.Module
import dagger.Provides


@Module
class ApolloModule {


    @Provides
    fun providerapollo(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constant.SERVER_URL)
            .build()
    }


}