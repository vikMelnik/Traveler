package com.example.traveler.model

//import com.example.traveler.model.entities.listEntities.Entities
import com.example.traveler.model.entities.listEntities.Restaurants

sealed class AppState{
	data class Success(val entity: Restaurants): AppState()
	data class Error(val error: Throwable): AppState()
	object Loading: AppState()
}
