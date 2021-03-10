package com.mdev.countriesapp.ui.fragments.countryList

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.data.model.SortType
import com.mdev.countriesapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: CountryRepository
    ) : ViewModel() {

    private var countriesList: Flow<PagingData<CountryDTO>>? = null


    fun loadCountries(): Flow<PagingData<CountryDTO>> {
        val newCountries = repository.fetchCountries().cachedIn(viewModelScope)
        countriesList = newCountries

        return newCountries
    }


    fun sortBy(sortBy:SortType): Flow<PagingData<CountryDTO>> {
        val newCountries = repository.sortCountries(sortBy).cachedIn(viewModelScope)
        countriesList = newCountries
        return newCountries
    }

}