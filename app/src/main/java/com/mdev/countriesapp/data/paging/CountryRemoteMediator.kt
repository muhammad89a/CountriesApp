package com.mdev.countriesapp.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mdev.countriesapp.data.db.AppDatabase
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.data.network.NetworkApis
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CountryRemoteMediator(
    private val database:AppDatabase,
    private val networkApis: NetworkApis):
    RemoteMediator<Int, CountryDTO>() {

    private val countryDao = database.countryDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, CountryDTO>):
            MediatorResult {
        return try {

            val response = networkApis.getAllCountries()
            database.withTransaction{
                if(loadType == LoadType.REFRESH){
                    Log.d("CountryRemoteMediator", "LoadType.REFRESH")
//                    countryDao.clearCountries()
                }
                Log.d("CountryRemoteMediator", "response=$response")
                response?.let {
                    countryDao.insertAllCountries(it)
                }
            }

            MediatorResult.Success(endOfPaginationReached = true)
        }catch (e: IOException){
            MediatorResult.Error(e)
        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}