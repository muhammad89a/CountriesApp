package com.mdev.countriesapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.data.network.NetworkApis
//
//class CountryPageSource(
//    private val networkApis: NetworkApis): PagingSource<Int, CountryDTO>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountryDTO> {
//        try {
//            val page = params.key?:1
//            val response = networkApis.getAllCountries();
//
//            val next = response.body()?.info?.next
//            val nextPage = next?.split("page=")?.get(1)?.toIntOrNull()
//            val prev:String? = response.body()?.info?.prev
//            val prevPage = prev?.split("page=")?.get(1)?.toIntOrNull()
//
//            val result:List<CountryDTO> = response.body()?.results.run { this as List<CountryDTO> }
//
//            return LoadResult.Page(
//                data = result,
//                prevKey = prevPage,
//                nextKey = nextPage
//            )
//        }catch (e:Throwable){
//            Log.e("Error","Item Page Source",e)
//            return LoadResult.Error(e)
//        }
//    }
//}