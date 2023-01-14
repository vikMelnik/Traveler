package com.example.traveler.model.repository


import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.model.entities.listEntities.getUkrCityRestaurant
import com.example.traveler.model.entities.listEntities.getWorldCityRestaurant

class RepositoryImpl<T>: Repository {
	//override fun getRestaurantFromServer() = Restaurants()
	//override fun getRestaurantFromLocalStorage() = Restaurants()

	override fun getRestaurantFromServerCity(): List<Restaurants> {
		TODO("Not yet implemented")
	}

	override fun getRestaurantFromLocalStorage() = Restaurants()

	override fun getRestaurantFromLocalStorageUkrCity() = getUkrCityRestaurant()

	override fun getRestaurantFromLocalStorageWorldCity() = getWorldCityRestaurant()



}