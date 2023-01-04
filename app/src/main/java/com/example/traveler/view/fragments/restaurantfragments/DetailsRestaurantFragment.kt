package com.example.traveler.view.fragments.restaurantfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveler.databinding.FragmentRestaurantDetailsBinding
import com.example.traveler.view.fragments.EntitiesFragment
import com.example.traveler.viewmodel.RestaurantsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsRestaurantFragment: EntitiesFragment() {

	companion object {
		fun newInstance() = DetailsRestaurantFragment()
	}

	private var _binding:FragmentRestaurantDetailsBinding? = null
	private val binding get() = _binding!!

	//Add koin ViewModel
	private val restaurantsViewModel: RestaurantsViewModel by viewModel()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentRestaurantDetailsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}