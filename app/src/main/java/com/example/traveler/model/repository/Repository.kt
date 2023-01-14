package com.example.traveler.model.repository

import com.example.traveler.model.entities.listEntities.Restaurants

interface Repository {
	fun getRestaurantFromServerCity(): List<Restaurants>
	//fun getRestaurantFromServer(): Restaurants
	fun getRestaurantFromLocalStorage(): Restaurants
	fun getRestaurantFromLocalStorageUkrCity(): List<Restaurants>
	fun getRestaurantFromLocalStorageWorldCity(): List<Restaurants>
}