package com.example.traveler.model.repository


import com.example.traveler.model.entities.listEntities.Restaurants

class RepositoryImpl<T>: Repository {
	override fun getRestaurantFromServer() = Restaurants()


	override fun getRestaurantFromLocalStorage() = Restaurants()

}