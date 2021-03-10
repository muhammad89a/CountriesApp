package com.mdev.countriesapp.ui.fragments.countryDetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.ui.views.viewHolder.CountryItemViewHolder

class CountryBordersAdapter(var countriesBorder: List<CountryDTO>) :
    RecyclerView.Adapter<CountryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int = countriesBorder.size

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        val country = countriesBorder[position]
        holder.bind(country,false)
    }

    fun setList(list: List<CountryDTO>) {
        val diffUtil = CountryDiffUtil(countriesBorder, list)
        val differResult = DiffUtil.calculateDiff(diffUtil)
        this.countriesBorder = list
        differResult.dispatchUpdatesTo(this)
    }
}