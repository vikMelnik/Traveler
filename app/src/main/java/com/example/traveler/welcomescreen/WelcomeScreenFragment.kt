package com.example.traveler.welcomescreen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.databinding.FragmentWelcomeScreenBinding
import com.example.traveler.startlist.StartListFragment

class WelcomeScreenFragment : ViewBindingFragment<FragmentWelcomeScreenBinding>(
	FragmentWelcomeScreenBinding::inflate
) {

	companion object {
		fun newInstance() = WelcomeScreenFragment()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupSpinnerCountry()

	}

	private fun setupSpinnerCountry() {
		val spinnerCountry = binding.nameSpinnerCountry
		val arrayAdapterCountry =
			ArrayAdapter.createFromResource(
				requireContext(), R.array.country,
				android.R.layout.simple_spinner_item
			)
		spinnerCountry.adapter = arrayAdapterCountry
		spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>,
				view: View,
				position: Int,
				id: Long
			) {
				val someCountry = parent.getItemAtPosition(position).toString()
				setupSpinner(someCountry)
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
			}
		}
	}

	private fun setupSpinner(some: String) {
		var someR = R.array.empty

		when (some) {
			"Ukraine" -> {
				someR = R.array.ukraine
			}
			"Poland" -> {
				someR = R.array.poland
			}
		}

		val spinnerCity = binding.nameSpinnerCity
		val arrayAdapterCity =
			ArrayAdapter.createFromResource(
				requireContext(), someR,
				android.R.layout.simple_spinner_item
			)
		spinnerCity.adapter = arrayAdapterCity

		spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>,
				view: View,
				position: Int,
				id: Long
			) {
				val chooseCity = parent.getItemAtPosition(position).toString()

				arguments = Bundle().apply {
					putString("ARG_COUNTRY", some)
					putString("ARG_CITY", chooseCity)
				}

				if (chooseCity != "ChooseCity") {

					parentFragmentManager.beginTransaction()
						.replace(R.id.container, StartListFragment.newInstance(arguments!!))
						.addToBackStack("")
						.commitAllowingStateLoss()
				}
			}

			override fun onNothingSelected(parent: AdapterView<*>) {
				// Code to perform some action when nothing is selected
			}
		}
	}
}



