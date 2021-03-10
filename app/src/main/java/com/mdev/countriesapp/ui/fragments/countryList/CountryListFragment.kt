package com.mdev.countriesapp.ui.fragments.countryList

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.paging.ExperimentalPagingApi
import com.mdev.countriesapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mdev.countriesapp.data.model.SortType
import com.mdev.countriesapp.databinding.CountryListFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalPagingApi
@AndroidEntryPoint
class CountryListFragment : Fragment(),LifecycleObserver  {

    private val viewModel: CountryListViewModel by hiltNavGraphViewModels(R.id.navgraph)
    private var job: Job? = null
    private lateinit var adapter: CountriesListAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var toolbar: Toolbar
    private var _binding: CountryListFragmentBinding? = null
    private val binding
        get() = _binding!!

    override
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CountryListFragmentBinding.inflate(inflater, container, false)

        setupCustomToolbar()
        initAdapter()
        getCountriesAndNotifyAdapter()
        showPopupMenu()

        return binding.root
    }

    private fun setupCustomToolbar() {
        toolbar = binding.countriesListToolbar
        updateToolbarTitle()
    }

    private fun initAdapter() {
        adapter = CountriesListAdapter()
        binding.countriesList.adapter = adapter

        adapter.addLoadStateListener { loadState ->
            binding.countriesList.isVisible = loadState.refresh is LoadState.NotLoading
            binding.progress.isVisible = loadState.refresh is LoadState.Loading
//            manageErrors(loadState)
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showPopupMenu() {
        binding.apply {
            toolbar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.sort_alphabetically->{
                        sortCountriesAndNotifyAdapter(sortBy = SortType.Alphabetical(it.isChecked))
                        it.isChecked = !it.isChecked
                        it.icon = if(it.isChecked){
                             resources.getDrawable(R.drawable.ic_menu_sort_alphabetically_desc,context?.theme)
                        }else{
                             resources.getDrawable(R.drawable.ic_menu_sort_alphabetically_asc,context?.theme)
                        }
                    }
                    R.id.sort_by_area->{
                        sortCountriesAndNotifyAdapter(sortBy = SortType.Area(it.isChecked))
                        it.isChecked = !it.isChecked
                        it.icon = if(it.isChecked){
                            resources.getDrawable(R.drawable.ic_sort_by_numeric_order_desc,null)
                        }else{
                            resources.getDrawable(R.drawable.ic_sort_by_numeric_order_asc,null)
                        }
                    }
                }
                true
            }
        }
    }

    private fun getCountriesAndNotifyAdapter() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.loadCountries().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun sortCountriesAndNotifyAdapter(sortBy:SortType) {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.sortBy(sortBy).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun updateToolbarTitle() {
        toolbar.title  = resources.getString(R.string.app_name)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}