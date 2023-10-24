package com.nikdev.graphqlapp.data

import com.apollographql.apollo3.ApolloClient
import com.nikdev.CountriesQuery
import com.nikdev.CountryQuery
import com.nikdev.graphqlapp.domain.CountryClient
import com.nikdev.graphqlapp.domain.DetailedCountry
import com.nikdev.graphqlapp.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}