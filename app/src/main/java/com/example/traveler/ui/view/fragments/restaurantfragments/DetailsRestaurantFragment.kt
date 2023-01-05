package com.example.traveler.ui.view.fragments.restaurantfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveler.R
import com.example.traveler.databinding.FragmentRestaurantDetailsBinding
import com.example.traveler.utilites.hide
import com.example.traveler.model.AppState
import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.utilites.show
import com.example.traveler.ui.view.fragments.EntitiesFragment
import com.example.traveler.viewmodel.RestaurantsViewModel
import com.google.android.material.snackbar.Snackbar
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

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		restaurantsViewModel.liveData.observe(viewLifecycleOwner){renderData(it)}
		restaurantsViewModel.getEntities()
	}

	private fun renderData(appState: AppState) = with(binding) {
		when (appState) {
			is AppState.Success<*> -> {
				progressBar.hide()
				widgetGroup.show()
				val restaurantData = appState.data
				setData(restaurantData as Restaurants)
			}
			is AppState.Error -> {
				progressBar.hide()
				widgetGroup.hide()
			     snakeShow()
			}
			AppState.Loading -> {
				progressBar.show()
				widgetGroup.hide()
			}

		}
	}

	private fun snakeShow() {
		Snackbar
			.make(binding.mainDetail, "ERROR!!!", Snackbar.LENGTH_LONG)
			.show()
	}

	private fun setData(restaurantData: Restaurants) = with(binding) {

		nameRestaurant.text = restaurantData.name
		rating.text = restaurantData.rating.toString()
		picImg.setImageResource(R.drawable.food)
		timeWork.text = restaurantData.timeWork
		description.text = restaurantData.description
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}