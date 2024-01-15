package com.example.lib_apollographql

import com.apollographql.apollo3.ApolloClient

class ApolloClientManager {
    companion object {
        fun provideApolloClient(): ApolloClient {
            return ApolloClient.Builder()
                .serverUrl("https://countries.trevorblades.com/graphql")
                .build()
        }
    }
}