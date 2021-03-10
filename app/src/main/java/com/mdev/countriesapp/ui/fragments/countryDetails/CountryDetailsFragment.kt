package com.mdev.countriesapp.ui.fragments.countryDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mdev.countriesapp.R
import com.mdev.countriesapp.data.model.CountryDTO
import com.mdev.countriesapp.databinding.CountryDetailsFragmentBinding
import com.mdev.countriesapp.ui.fragments.countryDetails.adapter.CountryBordersAdapter
import com.mdev.countriesapp.utils.loadImageOrDefault
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private val countryDetailsViewModel: CountryDetailsViewModel by hiltNavGraphViewModels(R.id.navgraph)
    private val args: CountryDetailsFragmentArgs by navArgs()
    private val country: CountryDTO by lazy { args.selectedCountry }
    private var _binding: CountryDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val countryBordersAdapter by lazy { CountryBordersAdapter(arrayListOf()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CountryDetailsFragmentBinding.inflate(inflater, container, false)
        setupRecyclerView()
        countryDetailsViewModel.fetchCountry(country.alpha3Code)
        countryDetailsViewModel.fetchCountryBorders(country.borders?: emptyList())

        countryDetailsViewModel.countryLiveData.observe(viewLifecycleOwner) { country ->
            displayCountryDetails(country)
        }

        countryDetailsViewModel.countryBordersLiveData.observe(viewLifecycleOwner) { countries ->
            displayBorderCountryDetails(countries)
        }

        return binding.root
    }


    private fun displayCountryDetails(selectedArticle: CountryDTO) {
        binding.apply {
            countriesFlag.loadImageOrDefault(selectedArticle.flagUrl?:"")
            nativeNameCountry.text = selectedArticle.nativeName?:""
            enNameCountry.text = selectedArticle.name?:""

            countriesDetailsToolbar.title = selectedArticle.name?:""
            countriesDetailsToolbar.setNavigationOnClickListener{
                findNavController().navigateUp()
            }
        }
    }
    private fun displayBorderCountryDetails(countries: List<CountryDTO>) {
        Log.v("displayBorderCountry","countries=> ${countries}")
        countryBordersAdapter.setList(countries)

    }


    private fun setupRecyclerView() {
        binding.bordersCountryList.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = countryBordersAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}