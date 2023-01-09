package com.example.traveler.ui.restaurans

import android.os.Bundle
import android.view.View
import com.example.traveler.databinding.FragmentRestaurantsListRecyclerBinding
import com.example.traveler.model.AppState
import com.example.traveler.ui.view.fragments.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantListFragment: ViewBindingFragment<FragmentRestaurantsListRecyclerBinding>(
	FragmentRestaurantsListRecyclerBinding::inflate) {

	//Add koin ViewModel
	private val restaurantListViewModel: RestaurantsListViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		restaurantListViewModel.liveData.observe(viewLifecycleOwner){renderData(it)}
		restaurantListViewModel.getEntities()
	}

	private fun renderData(appState: AppState) = with(binding) {
		when (appState) {
			is AppState.Success<*> -> {

			}
			is AppState.Error -> {

			}
			AppState.Loading -> {

			}
		}
	}
}