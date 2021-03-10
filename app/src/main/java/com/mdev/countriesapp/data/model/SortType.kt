package com.mdev.countriesapp.data.model

sealed class SortType{
        data class Area(val  isASC: Boolean ) : SortType()
        data class Alphabetical(val  isASC: Boolean ) : SortType()
}
