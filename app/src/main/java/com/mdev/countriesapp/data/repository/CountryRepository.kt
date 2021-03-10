package com.mdev.countriesapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mdev.countriesapp.data.db.AppDatabase
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.data.model.SortType
import com.mdev.countriesapp.data.network.NetworkApis
import com.mdev.countriesapp.data.paging.CountryRemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CountryRepository @Inject constructor(
    private val service: NetworkApis,
    private val database: AppDatabase
) {

    @ExperimentalPagingApi
    fun fetchCountries(): Flow<PagingData<CountryDTO>> {
        val pagingSourceFactory = { database.countryDao().getAllCountries() }
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE, maxSize = 1000, enablePlaceholders = true),
            remoteMediator = CountryRemoteMediator(database,service),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @ExperimentalPagingApi
    fun sortCountries(sortBy: SortType ): Flow<PagingData<CountryDTO>> {
        val pagingSourceFactory = when(sortBy){
            is SortType.Area->{
                if(sortBy.isASC){
                    {database.countryDao().sortCountriesByAreaASC()}
                }else{
                    {database.countryDao().sortCountriesByAreaDESC()}
                }
            }
            is SortType.Alphabetical->{
                if(sortBy.isASC){
                    {database.countryDao().sortCountriesByNameASC()}
                }else{
                    {database.countryDao().sortCountriesByNameDESC()}
                }
            }
        }
//        val pagingSourceFactory = { database.countryDao().getAllCountries() }
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE, maxSize = 1000, enablePlaceholders = true),
//            remoteMediator = CountryRemoteMediator(database,service),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getCountryBorders(alpha3Codes: List<String>) = flow {
        val country = database.countryDao().getCountryBorders(alpha3Codes)
        emit(country)
    }.flowOn(Dispatchers.Default)

    fun getCountry(alpha3Code: String) = flow {
        val country = database.countryDao().getCountryByAlpha3Code(alpha3Code)
        emit(country)
    }.flowOn(Dispatchers.Default)

    companion object {
        private const val NETWORK_PAGE_SIZE = 300
    }
}
