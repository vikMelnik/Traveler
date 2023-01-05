package com.example.traveler.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveler.databinding.FragmentRestaurantDetailsBinding
import com.example.traveler.model.AppState
import com.example.traveler.model.entities.listEntities.Restaurants


abstract class EntitiesFragment : Fragment() {

	abstract override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View


}



