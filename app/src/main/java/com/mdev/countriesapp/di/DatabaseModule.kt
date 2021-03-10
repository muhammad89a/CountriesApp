package com.mdev.countriesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mdev.countriesapp.data.db.AppDatabase
import com.mdev.countriesapp.data.db.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "CountryDB.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao{
        return appDatabase.countryDao()
    }
}