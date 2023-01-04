package com.example.traveler.model.repository

import com.example.traveler.model.entities.listEntities.Entities
import com.example.traveler.model.entities.listEntities.Restaurants

class RepositoryImpl: Repository {
	override fun getEntitiesFromServer() = Entities()
	//override fun getEntitiesFromServer() = Restaurants()

	override fun getEntitiesFromLocalStorage() = Restaurants()
}