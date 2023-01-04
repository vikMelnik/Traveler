package com.example.traveler.view.fragments.restaurantfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.traveler.R
import com.example.traveler.databinding.FragmentRestaurantDetailsBinding
import com.example.traveler.model.AppState
import com.example.traveler.model.entities.listEntities.Entities
import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.view.fragments.EntitiesFragment
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
//		val observer = Observer<AppState>{renderData(it)}
//		restaurantsViewModel.liveData.observe(viewLifecycleOwner, observer)
		restaurantsViewModel.liveData.observe(viewLifecycleOwner){renderData(it)}
		restaurantsViewModel.getRestaurant()
	}

	private fun renderData(appState: AppState) = with(binding) {
		when (appState) {
			is AppState.Success -> {
				progressBar.visibility = View.GONE
				widgetGroup.visibility = View.VISIBLE
				val restaurantData = appState.entity
				setData(restaurantData)
			}
			is AppState.Error -> {
				progressBar.visibility = View.GONE
				widgetGroup.visibility = View.INVISIBLE
				Snackbar
					.make(mainDetail, "ERROR!!!", Snackbar.LENGTH_LONG)
					.show()
			}
			AppState.Loading -> {
				progressBar.visibility = View.VISIBLE
				widgetGroup.visibility = View.INVISIBLE
			}
		}
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