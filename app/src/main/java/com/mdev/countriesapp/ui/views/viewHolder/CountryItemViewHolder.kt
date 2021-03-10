package com.mdev.countriesapp.ui.views.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.databinding.CountryItemListBinding
import com.mdev.countriesapp.ui.fragments.countryList.CountryListFragmentDirections
import com.mdev.countriesapp.utils.loadImageOrDefault
import com.mdev.countriesapp.utils.showAreaOrNotFound


class CountryItemViewHolder(private val binding: CountryItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(countryItem: CountryDTO,isEnabled:Boolean = true) {
        binding.run {
            enNameCountryItemList.text = countryItem.name?:""
            nativeNameCountryItemList.text = countryItem.nativeName?:""
            areaCountryItemList.showAreaOrNotFound(countryItem.area)
            countryItem.flagUrl?.let {
                flagImgCountryItemList.loadImageOrDefault(countryItem.flagUrl)
            }
            if (isEnabled){
                setOnClickListener(countryItem)
            }
        }
    }

    private fun setOnClickListener(country: CountryDTO) {
        binding.root.setOnClickListener{view ->
            navigateToDetail(country, view)
        }
    }

    private fun navigateToDetail(country: CountryDTO, view: View) {
        val directions = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(country)
        view.findNavController().navigate(directions)
    }

    companion object {
        fun from(parent: ViewGroup): CountryItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CountryItemListBinding.inflate(layoutInflater, parent, false)
            return CountryItemViewHolder(binding)
        }
    }
}