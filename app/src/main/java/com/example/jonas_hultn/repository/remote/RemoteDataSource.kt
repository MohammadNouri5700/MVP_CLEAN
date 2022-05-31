package com.example.jonas_hultn.repository.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.factory.BaseDataSource
import com.example.jonas_hultn.repository.service.BallonService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val apolloCliascent: ApolloClient): BaseDataSource() {

    @Inject
    lateinit var ballonService: BallonService

    suspend fun getBallonList(after:String): ApolloResponse<BallonlistQuery.Data> { return ballonService.getlist(apolloClient = apolloCliascent,after)}

}