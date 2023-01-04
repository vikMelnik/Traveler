package com.example.traveler.model.repository

import com.example.traveler.model.entities.listEntities.Entities
import com.example.traveler.model.entities.listEntities.Restaurants

//import com.example.traveler.model.entities.listEntities.Restaurants

interface Repository {
	fun getEntitiesFromServer(): Entities
	fun getEntitiesFromLocalStorage(): Restaurants
}