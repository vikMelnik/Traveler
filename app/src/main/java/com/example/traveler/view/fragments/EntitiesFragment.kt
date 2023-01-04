package com.example.traveler.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveler.databinding.FragmentRestaurantDetailsBinding


abstract class EntitiesFragment : Fragment() {



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}
	abstract override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View


}



