package com.example.traveler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traveler.R
import com.example.traveler.base.view.ViewBindingFragment
import com.example.traveler.startlist.StartListFragment
import com.example.traveler.welcomescreen.WelcomeFragment
import com.example.traveler.welcomescreen.WelcomeScreenFragment


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, WelcomeScreenFragment.newInstance())
				.commitNow()
		}
	}
}