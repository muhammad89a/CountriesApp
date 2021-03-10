package com.mdev.countriesapp.ui.fragments.countryDetails.adapter


import androidx.recyclerview.widget.DiffUtil
import com.mdev.countriesapp.data.model.CountryDTO

class CountryDiffUtil(
    private val oldList: List<CountryDTO>,
    private val newList: List<CountryDTO>

) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].alpha3Code == newList[newItemPosition].alpha3Code
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].nativeName == newList[newItemPosition].nativeName
                && oldList[oldItemPosition].flagUrl == newList[newItemPosition].flagUrl
                && oldList[oldItemPosition].borders == newList[newItemPosition].borders
    }
}