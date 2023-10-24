package com.nikdev.graphqlapp.domain

class GetCountryUesCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient
            .getCountry(code)
    }
}