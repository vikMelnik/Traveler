package com.example.traveler.restaurants.restaurantfragments

import android.os.Bundle
import android.view.View
import com.example.traveler.R
import com.example.traveler.databinding.FragmentRestaurantsListRecyclerBinding
import com.example.traveler.base.AppState
import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.restaurants.RestaurantListAdapter
import com.example.traveler.restaurants.viewmodel.RestaurantsListViewModel
import com.example.traveler.ui.view.StartListFragment
import com.example.traveler.utilites.hide
import com.example.traveler.utilites.show
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST")
class RestaurantListFragment: ViewBindingFragment<FragmentRestaurantsListRecyclerBinding>(
	FragmentRestaurantsListRecyclerBinding::inflate) {

	companion object {
		fun newInstance() = RestaurantListFragment()	}


	interface OnItemViewClickListener{
		fun onItemViewClick(restaurants: Restaurants)
	}

	private var adapter: RestaurantListAdapter? = null
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
				progressBar.hide()
				adapter = RestaurantListAdapter(object : OnItemViewClickListener {
					override fun onItemViewClick(rest: Restaurants) {val manager = activity?.supportFragmentManager
						manager?.let { manager ->
							val bundle = Bundle().apply {
								putParcelable(DetailsRestaurantFragment.BUNDLE_EXTRA, rest)
							}
							manager.beginTransaction()
								.replace(R.id.container, DetailsRestaurantFragment.newInstance(bundle))
								.addToBackStack("")
								.commitAllowingStateLoss()
						}
					}
				}).apply {
					setRestaurant(appState.data as List<Restaurants>)
					//setMovies(appState.moviesData)
				}
				restaurantListRecycler.adapter = adapter
			//}
//				progressBar.hide()
//				//widgetGroup.show()
//				val restaurantList = appState.data
//				setData(restaurantList as List<Restaurants>)
			}
			is AppState.Error -> {
				progressBar.hide()
				//widgetGroup.hide()
				snakeShow()
			}
			AppState.Loading -> {
				progressBar.show()
				//widgetGroup.hide()
			}
		}
	}

	private fun snakeShow() {
		Snackbar
			.make(binding.root, "ERROR!!!", Snackbar.LENGTH_LONG)
			.show()
	}
}