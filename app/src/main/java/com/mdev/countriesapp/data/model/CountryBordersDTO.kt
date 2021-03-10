package com.mdev.countriesapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CountryBordersDTO(var countryBorders: List<String>):Parcelable