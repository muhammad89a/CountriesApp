package com.mdev.countriesapp.ui.fragments.countryList

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.ui.views.viewHolder.CountryItemViewHolder

class CountriesListAdapter :
    PagingDataAdapter<CountryDTO, CountryItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        val country = getItem(position) ?: return
        holder.bind(country)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CountryDTO> =
            object : DiffUtil.ItemCallback<CountryDTO>() {
                override fun areItemsTheSame(oldItem: CountryDTO, newItem: CountryDTO) =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: CountryDTO, newItem: CountryDTO) =
                    oldItem.alpha3Code == newItem.alpha3Code
            }
    }
}
