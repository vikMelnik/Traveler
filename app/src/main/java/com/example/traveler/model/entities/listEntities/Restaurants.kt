package com.example.traveler.model.entities.listEntities

import com.example.traveler.R
import com.example.traveler.model.entities.City

data class Restaurants(
	val city: City = getDefaultCity(),
	val name: String = "Amsterdam",
	val picImg: Int = R.drawable.picture,
	val rating: Double = 5.7,
	val timeWork: String = "Work: from 7-00 till 23-00",
	val description: String = "Hamburger McDonald's Fast Food Restaurant, Menu." )

fun getDefaultCity(): City = City("Kyiv", "Ukraine")
