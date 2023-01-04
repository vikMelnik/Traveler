package com.example.traveler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traveler.R
import com.example.traveler.view.fragments.StartListFragment
import com.example.traveler.view.fragments.restaurantfragments.DetailsRestaurantFragment


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, DetailsRestaurantFragment.newInstance())
				.commitNow()
		}
	}
}