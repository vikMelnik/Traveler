package com.example.traveler.welcomescreen
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.databinding.FragmentWelcomeScreenBinding
import com.example.traveler.startlist.StartListFragment
import kotlinx.coroutines.*
import java.util.*



private const val REFRESH_PERIOD = 1000L
private const val MINIMAL_DISTANCE = 1f


class WelcomeScreenFragment : ViewBindingFragment<FragmentWelcomeScreenBinding>(
	FragmentWelcomeScreenBinding::inflate
), CoroutineScope by MainScope() {

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

	private val onLocationListener = object : LocationListener {
		override fun onLocationChanged(location: Location) {
			Log.d("@@@", location.toString())
			getAddressByLocation( location)

		}
		override fun onStatusChanged(provider: String, status: Int, extras: Bundle)
		{}
		override fun onProviderEnabled(provider: String) {}
		override fun onProviderDisabled(provider: String) {}
	}

	private fun getAddressByLocation(location: Location) {
	val geocoder = Geocoder(requireContext())

		launch {
			val job = async(Dispatchers.Main) {
				geocoder.getFromLocation(location.latitude, location.longitude, 1)
			}
			val addresses = job.await()
			if (isActive){
				binding.mainFragmentFABLocation.post { showAddressDialog(addresses!![0].getAddressLine(0), location) }

			}
		}
	}

	@SuppressLint("MissingPermission")
	private fun getLocation() {
		context?.let {
			val locationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
				//val providerGPS = locationManager.getBestProvider(Criteria(),true)
				val providerGPS =
					locationManager.getProvider(LocationManager.GPS_PROVIDER) // getBestProvider trying (COLD START)
				providerGPS?.let {
					// Будем получать геоположение через каждые 60 секунд или каждые 100 метров
					locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER,
						REFRESH_PERIOD,
						MINIMAL_DISTANCE,
						onLocationListener
					)
				}
			} else{
				val location =
					locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
				if (location == null) {
					Toast.makeText(requireContext()," GPS turn off", Toast.LENGTH_LONG).show()
				} else {
					getAddressByLocation(location)
				}
			}
		}
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

	private fun showAddressDialog(address: String, location: Location) {
		activity?.let {
			AlertDialog.Builder(it)
				.setTitle(getString(R.string.dialog_address_title))
				.setMessage(address)
				.setPositiveButton(" Something !!!") {
						_, _ ->
//					openDetailsFragment(
//						Weather(
//							City(
//								address,
//								location.latitude,
//								location.longitude
//							)
//						)
//					)
				}
				.setNegativeButton(getString(R.string.dialog_button_close)) { dialog,
				                                                              _ -> dialog.dismiss() }
				.create()
			.show()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		cancel()
	}
}



