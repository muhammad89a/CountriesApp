package com.mdev.countriesapp.ui.fragments.countryDetails

import androidx.lifecycle.*
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _countryAlpha3Code = MutableLiveData<String>()

    val countryLiveData: LiveData<CountryDTO> = _countryAlpha3Code.switchMap { code->
        repository.getCountry(code).asLiveData()
    }

    fun fetchCountry(id: String) {
        _countryAlpha3Code.value = id
    }


    private val _countryBorders = MutableLiveData<List<String>>()

    val countryBordersLiveData: LiveData<List<CountryDTO>> = _countryBorders.switchMap { codes->
        repository.getCountryBorders(codes).asLiveData()
    }

    fun fetchCountryBorders(alpha3Codes: List<String>) {
        _countryBorders.value = alpha3Codes
    }
}