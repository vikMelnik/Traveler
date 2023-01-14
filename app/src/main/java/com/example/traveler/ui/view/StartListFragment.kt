package com.example.traveler.ui.view

import android.os.Bundle
import android.view.View
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.databinding.FragmentStartListBinding
import com.example.traveler.model.entities.GeneralListTraveler
import com.example.traveler.model.entities.TYPE_HEADER
import com.example.traveler.model.entities.TYPE_NAME
import com.example.traveler.restaurants.restaurantfragments.RestaurantListFragment

class StartListFragment : ViewBindingFragment<FragmentStartListBinding>(
	FragmentStartListBinding::inflate) {

	interface OnItemViewClickListener{
		fun onItemViewClick(startList: GeneralListTraveler)
	}

	companion object {
		fun newInstance() = StartListFragment()	}

	//private val viewModel: MainViewModel by viewModel()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.startListRecycler.adapter = StartListRecyclerAdapter(getGeneralList()
		,object : OnItemViewClickListener {
			override fun onItemViewClick(startList: GeneralListTraveler) {
				when (startList.name) {
					"Restaurants" -> {
						parentFragmentManager
							.beginTransaction()
							.replace(R.id.container, RestaurantListFragment.newInstance())
							.addToBackStack("")
							.commitAllowingStateLoss()
					}
				}
					}
				}
			 )

	}

	private fun getGeneralList(): ArrayList<GeneralListTraveler> {
		val listGeneral = arrayListOf<GeneralListTraveler>()
		listGeneral.add(GeneralListTraveler(getString(R.string.header),R.drawable.bg_earth, TYPE_HEADER))
		for (i in this.resources.getStringArray(R.array.foodList).toList()){
			listGeneral.add(GeneralListTraveler(i,R.drawable.pngwing1, TYPE_NAME))
		}
		for (i in this.resources.getStringArray(R.array.lifeList).toList()){
			listGeneral.add(GeneralListTraveler(i,R.drawable.rest, TYPE_NAME))
		}
		for (i in this.resources.getStringArray(R.array.servicesList).toList()){
			listGeneral.add(GeneralListTraveler(i,R.drawable.cleaner, TYPE_NAME))
		}
		return listGeneral
	}

}

