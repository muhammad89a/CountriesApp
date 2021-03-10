package com.mdev.countriesapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdev.countriesapp.data.model.CountryDTO

@Dao
interface CountryDao {

    @Query("SELECT * FROM Countries")
    fun getAllCountries(): PagingSource<Int, CountryDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(items:List<CountryDTO>)

    @Query("DELETE FROM Countries")
    suspend fun clearCountries()

    @Query("SELECT * FROM Countries WHERE alpha3Code = :alpha3Code")
    suspend fun getCountryByAlpha3Code(alpha3Code: String): CountryDTO

    @Query("SELECT * FROM Countries WHERE alpha3Code In(:alpha3Codes)")
    suspend fun getCountryBorders(alpha3Codes: List<String>): List<CountryDTO>

    //sort
    @Query("SELECT * FROM Countries Order by name")
    fun sortCountriesByNameASC(): PagingSource<Int, CountryDTO>

    @Query("SELECT * FROM Countries Order by name desc")
    fun sortCountriesByNameDESC(): PagingSource<Int, CountryDTO>

    @Query("SELECT * FROM Countries Order by area")
    fun sortCountriesByAreaASC(): PagingSource<Int, CountryDTO>

    @Query("SELECT * FROM Countries Order by area desc")
    fun sortCountriesByAreaDESC(): PagingSource<Int, CountryDTO>




}