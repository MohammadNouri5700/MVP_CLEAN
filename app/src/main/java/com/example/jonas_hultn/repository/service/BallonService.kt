package com.example.jonas_hultn.repository.service

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.jonas_hultn.BallonlistQuery
import javax.inject.Inject

class BallonService @Inject constructor() {


    suspend fun getlist(apolloClient: ApolloClient): ApolloResponse<BallonlistQuery.Data> {
       return apolloClient.query(BallonlistQuery()).execute()
    }

}