package com.example.jonas_hultn.repository.service

import android.util.Log
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.jonas_hultn.BallonlistQuery
import javax.inject.Inject

class BallonService @Inject constructor() {


    suspend fun getlist(
        apolloClient: ApolloClient,
        after: String
    ): ApolloResponse<BallonlistQuery.Data> {
        try {
            return if (after == "") {
                apolloClient.query(BallonlistQuery()).execute()
            } else
                apolloClient.query(BallonlistQuery(Optional.presentIfNotNull(after))).execute()

        } catch (exception: ApolloException) {
            Log.e("App", exception.message.toString())
            return apolloClient.query(BallonlistQuery(Optional.presentIfNotNull(after))).execute()

        }
    }

}