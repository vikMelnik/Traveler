package com.example.traveler.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveler.R
import com.example.traveler.databinding.FragmentStartListBinding
import com.example.traveler.model.entities.GeneralListTraveler
import com.example.traveler.model.entities.TYPE_HEADER
import com.example.traveler.model.entities.TYPE_NAME
import com.example.traveler.ui.view.adapter.StartListRecyclerAdapter

class StartListFragment : ViewBindingFragment<FragmentStartListBinding>(
	FragmentStartListBinding::inflate) {

	companion object {
		fun newInstance() = StartListFragment()	}

	//private val viewModel: MainViewModel by viewModel()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.startListRecycler.adapter = StartListRecyclerAdapter(getGeneralList())
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

