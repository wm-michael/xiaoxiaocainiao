package com.example.lib_apollographql

import com.example.CountriesQuery
import com.example.CountryQuery

class ApolloCountryClient {

    val apolloClient = ApolloClientManager.provideApolloClient()


    suspend fun getCountries_raw_data(): List<CountriesQuery.Country> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?: emptyList()
    }

    suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }

}