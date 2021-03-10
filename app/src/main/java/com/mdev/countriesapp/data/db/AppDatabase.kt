package com.mdev.countriesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mdev.countriesapp.data.model.CountryDTO



@Database(entities = [CountryDTO::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase(){
    abstract fun countryDao(): CountryDao
}