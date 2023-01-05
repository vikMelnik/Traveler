package com.example.traveler.model.repository

import com.example.traveler.model.entities.listEntities.Restaurants

interface Repository {
	fun getRestaurantFromServer(): Restaurants
	fun getRestaurantFromLocalStorage(): Restaurants
}