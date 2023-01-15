package com.example.traveler.welcomescreen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.databinding.FragmentWelcomeScreenBinding
import com.example.traveler.ui.view.StartListFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class WelcomeScreenFragment : ViewBindingFragment<FragmentWelcomeScreenBinding>(
	FragmentWelcomeScreenBinding::inflate
) {

	companion object {
		fun newInstance() = WelcomeScreenFragment()
	}

	// TODO: Rename and change types of parameters
	private var param1: String? = null
	private var param2: String? = null


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupSpinnerCountry()


//		arguments?.let {
//			param1 = it.getString(ARG_PARAM1)
//			param2 = it.getString(ARG_PARAM2)
	}

	private fun setupSpinnerCountry() {
		val spinnerCountry = binding.nameSpinnerCountry
		val arrayAdapterCountry =
			ArrayAdapter.createFromResource(requireContext(),R.array.country,
				android.R.layout.simple_spinner_item)
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
			ArrayAdapter.createFromResource(requireContext(),someR,
				android.R.layout.simple_spinner_item)
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
					putString( "ARG_CITY", chooseCity)
				}

					if (chooseCity != "ChooseCity"){

				parentFragmentManager.beginTransaction()
					.replace(R.id.container, StartListFragment.newInstance(arguments!!))
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

	}
}



