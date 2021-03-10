package com.mdev.countriesapp.data.network

import com.mdev.countriesapp.data.model.CountryDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApis {

    @GET("all")
    suspend fun getAllCountries(): List<CountryDTO>?

    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): CountryDTO?

}