package com.mdev.countriesapp.di

import com.mdev.countriesapp.data.db.AppDatabase
import com.mdev.countriesapp.data.network.NetworkApis
import com.mdev.countriesapp.data.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCountryRepository(
        service: NetworkApis,
        database: AppDatabase
    ): CountryRepository {
        return CountryRepository(service, database)
    }
}