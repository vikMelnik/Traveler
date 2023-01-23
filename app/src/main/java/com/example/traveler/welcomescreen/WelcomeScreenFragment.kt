package com.example.traveler.welcomescreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
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

		setupFabLocation()
		setupSpinnerCountry()

	}

	private fun setupFabLocation() {
		binding.mainFragmentFABLocation.setOnClickListener {
			checkPermission()
		}
	}

	private fun checkPermission() {
		if (ContextCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_FINE_LOCATION
		) == PackageManager.PERMISSION_GRANTED){
			getLocation()
		} else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
			// важно написать убедительную просьбу
			explain()
		} else {
			mRequestPermission()
		}

	}

	private fun getLocation() {

	}

	private val REQUEST_CODE = 998
	private fun mRequestPermission() {
		requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		if(requestCode == REQUEST_CODE){
			for (i in permissions.indices){
				if (permissions[i] == Manifest.permission.ACCESS_FINE_LOCATION
					&& grantResults[i] == PackageManager.PERMISSION_GRANTED){
					getLocation()
				} else {
					explain()
				}
			}
		} else {
			super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		}
	}

	private fun explain() {
		AlertDialog.Builder(requireContext())
			.setTitle(resources.getString(R.string.dialog_rationale_title))
			.setMessage(resources.getString(R.string.dialog_rationale_meaasge))
			.setPositiveButton(resources.getString(R.string.dialog_rationale_give_access))
			{ _, _ ->
				mRequestPermission()
			}
			.setNegativeButton(getString(R.string.dialog_rationale_decline)) {
					dialog, _ -> dialog.dismiss() }
			.create()
			.show()

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

				if (chooseCity != "Choose City") {

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



