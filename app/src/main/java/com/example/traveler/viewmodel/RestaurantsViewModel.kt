package com.example.traveler.viewmodel

import com.example.traveler.model.AppState
import com.example.traveler.model.repository.Repository


class RestaurantsViewModel(private val repository: Repository): MainViewModel() {

	override fun getEntities() = getDataFromLocalSource()

	private fun getDataFromLocalSource() {
		localLiveData.value = AppState.Loading
		Thread {
			Thread.sleep(3000)
			localLiveData.postValue(AppState.Success(repository.getRestaurantFromLocalStorage()))
		}.start()
	}
}