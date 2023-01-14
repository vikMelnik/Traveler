package com.example.traveler.welcomescreen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.databinding.FragmentWelcomeScreenBinding
import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.restaurants.restaurantfragments.RestaurantListFragment
import com.example.traveler.ui.view.StartListFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class WelcomeScreenFragment : ViewBindingFragment<FragmentWelcomeScreenBinding>(
	FragmentWelcomeScreenBinding::inflate
) {

	interface OnItemViewClickListener{
		fun onItemViewClick()
	}

	companion object {
		fun newInstance() = WelcomeScreenFragment()
	}

	// TODO: Rename and change types of parameters
	private var param1: String? = null
	private var param2: String? = null
	private var isFlag = false

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		isFlag = setupSpinner()

		if (isFlag) {
			parentFragmentManager.beginTransaction()
			.replace(R.id.container, StartListFragment.newInstance())
			.addToBackStack("")
			.commitAllowingStateLoss()
		}


//		arguments?.let {
//			param1 = it.getString(ARG_PARAM1)
//			param2 = it.getString(ARG_PARAM2)
	}


	private fun setupSpinner(): Boolean {


		var isF = true
		val spinner = binding.nameSpinnerCity
		val arrayAdapter =
			ArrayAdapter.createFromResource(requireContext(),R.array.ukraine,
				android.R.layout.simple_spinner_item)
		spinner.adapter = arrayAdapter

		spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>,
				view: View,
				position: Int,
				id: Long
			) {


					var chooseCt = parent.getItemAtPosition(position).toString()

					if (chooseCt != "false"){

				parentFragmentManager.beginTransaction()
					.add(R.id.container, StartListFragment.newInstance())
					.addToBackStack("")
					.commitAllowingStateLoss()
			}

				//var chooseCity = parent.getItemAtPosition(position)



//				object : WelcomeScreenFragment.OnItemViewClickListener {
//					override fun onItemViewClick(chooser:Spinner) {
//						val manager = activity?.supportFragmentManager
//						manager?.let { manager ->
//							val bundle = Bundle().apply {
//								chooseCity
//								//putParcelable(StartListFragment.BUNDLE_EXTRA, chooseCity)
//							}
//							manager.beginTransaction()
//								.replace(R.id.container, StartListFragment.newInstance(bundle))
//								.addToBackStack("")
//								.commitAllowingStateLoss()
//						}
//					}
//				}
//						Toast.makeText(
//							requireContext(),
//							"Selected: $chooseCt  $isF",
//							Toast.LENGTH_SHORT
//						).show()

					}

			override fun onNothingSelected(parent: AdapterView<*>) {
				// Code to perform some action when nothing is selected
			}
		}


		/**
		companion object {
		/**
		 * Use this factory method to create a new instance of
		 * this fragment using the provided parameters.
		 *
		 * @param param1 Parameter 1.
		 * @param param2 Parameter 2.
		 * @return A new instance of fragment WelcomeScreenFragment.
		*/
		// TODO: Rename and change types and number of parameters
		@JvmStatic
		fun newInstance(param1: String, param2: String) =
		WelcomeScreenFragment().apply {
		arguments = Bundle().apply {
		putString(ARG_PARAM1, param1)
		putString(ARG_PARAM2, param2)
		}
		}
		}
		 */
       return false
	}
}



