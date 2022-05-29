package com.example.jonas_hultn.factory

import android.util.Log
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.jonas_hultn.BallonlistQuery


abstract class BaseDataSource {


    protected suspend fun <T> getResult(call: () -> ApolloCall<BallonlistQuery.Data>): ApolloResponse<BallonlistQuery.Data> {
//        try {
        val response = call().execute()
        return response
//            if (response.) {
//                val body = response.body()
//                if (body != null) return Resource.success(body)
//            }
//            return error(" ${response.code()} ${response.message()}")
//        } catch (e: Exception) {
//            return error(e.message ?: e.toString())
//        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        return Resource.error("Network call has failed for a following reason: $message")
    }
}