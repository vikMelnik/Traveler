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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.databinding.FragmentWelcomeScreenBinding
import kotlinx.coroutines.*
import java.util.*


private const val REFRESH_PERIOD = 1000L
private const val MINIMAL_DISTANCE = 1f


class WelcomeFragment : ViewBindingFragment<FragmentWelcomeScreenBinding>(
	FragmentWelcomeScreenBinding::inflate
), CoroutineScope by MainScope() {

	companion object {
		fun newInstance() = WelcomeFragment()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.mainFragmentFABLocation.setOnClickListener { checkPermission() }

	}
	private val permissionResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
		if (isGranted) {
			getLocation()
		} else {
			Toast.makeText(
				context,
				getString(R.string.dialog_message_no_gps),
				Toast.LENGTH_SHORT
			).show()
		}
	}

	private val onLocationListener = object : LocationListener {
		override fun onLocationChanged(location: Location) {
			Log.d("@@@", location.toString())
			context?.let {
				getAddressAsync(it, location)
			}
		}

		override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
		override fun onProviderEnabled(provider: String) {}
		override fun onProviderDisabled(provider: String) {}
	}



	private fun checkPermission() {
		if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
			== PackageManager.PERMISSION_GRANTED
		) {
			getLocation()
		} else {
			permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
		}
	}


	@SuppressLint("MissingPermission")
	@Suppress("DEPRECATION")
	private fun getLocation() {
		context?.let { context ->
			val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
			if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER,
					REFRESH_PERIOD,
					MINIMAL_DISTANCE,
					onLocationListener
				)
			} else {
				val location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER) //NETWORK_PROVIDER
				if (location == null) {
					Toast.makeText(context, getString(R.string.dialog_title_gps_turned_off), Toast.LENGTH_SHORT).show()
				} else {
					getAddressAsync(context, location)
				}
			}
		}
	}
//
//	private val geocodeListener = Geocoder.GeocodeListener { addresses ->
//
//		// do something with the addresses list
//	}

	private fun getAddressAsync(context: Context, location: Location) {

		val geoCoder = Geocoder(context)
		launch {
			val job = async(Dispatchers.IO) {
				geoCoder.getFromLocation(location.latitude, location.longitude, 1)
			}
			val addresses = job.await()
			if (isActive) {
				Log.d("@@", location.toString())
					showAddressDialog(
						addresses!![0].getAddressLine(
							0
						), location
					)

			} else {

			}
		}


//		val geocoder = Geocoder(context)
//		if (Build.VERSION.SDK_INT >= 33) {
//			// declare here the geocodeListener, as it requires Android API 33
//			geocoder.getFromLocation(location.latitude, location.longitude, 1, geocodeListener)
//		} else {
//			val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
//			// For Android SDK < 33, the addresses list will be still obtained from the getFromLocation() method
//			//showAddressDialog(addresses!![0].getAddressLine(0), location)
//		}


//		@Suppress("DEPRECATION")
//		fun Geocoder.getAddress(
//			latitude: Double,
//			longitude: Double,
//			address: (android.location.Address?) -> Unit
//		) {
//
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//				getFromLocation(latitude, longitude, 1) { address(it.firstOrNull()) }
//				return
//			}
//
//			try {
//				address(getFromLocation(latitude, longitude, 1)?.firstOrNull())
//			} catch(e: Exception) {
//				//will catch if there is an internet problem
//				address(null)
//			}
//		}
//		Geocoder(context, Locale("in"))
//			.getAddress(location.latitude, location.longitude) { address: android.location.Address? ->
//				if (address != null) {
//
//					showAddressDialog(address.getAddressLine(0), location)
//					//do your logic
//				}
//			}

//		val geoCoder = Geocoder(context)
//				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//					geoCoder.getFromLocation(location.latitude,location.longitude,1,
//						object : Geocoder.GeocodeListener{
//						override fun onGeocode(addresses: MutableList<Address>) {
//
//
//								showAddressDialog(addresses[0].getAddressLine(0), location)
//
//							// code
//						}
//						override fun onError(errorMessage: String?) {
//							super.onError(errorMessage)
//
//						}
//
//					}
//					//geoCoder.getFromLocation(location.latitude, location.longitude, 1,
//					)
//				}
			}





	private fun showAddressDialog(address: String, location: Location) {
		activity?.let {
			AlertDialog.Builder(it)
				.setTitle(getString(R.string.dialog_address_title))
				.setMessage(address)
				.setPositiveButton(" Something ") { _, _ ->
//					openDetailsFragment(
//						Weather(City(address, location.latitude, location.longitude))
//					)
				}
				.setNegativeButton(getString(R.string.dialog_button_close)) { dialog, _ -> dialog.dismiss() }
				.create()
				.show()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		cancel()
	}
}



