package com.nikdev.graphqlapp.di

import com.apollographql.apollo3.ApolloClient
import com.nikdev.graphqlapp.data.ApolloCountryClient
import com.nikdev.graphqlapp.domain.CountryClient
import com.nikdev.graphqlapp.domain.GetCountriesUesCase
import com.nikdev.graphqlapp.domain.GetCountryUesCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryClient: CountryClient): GetCountriesUesCase {
        return GetCountriesUesCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(countryClient: CountryClient): GetCountryUesCase {
        return GetCountryUesCase(countryClient)
    }
}