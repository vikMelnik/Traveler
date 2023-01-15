package com.example.traveler.restaurants.viewmodel

import com.example.traveler.base.AppState
import com.example.traveler.base.viewmodel.MainViewModel
import com.example.traveler.model.repository.Repository


class RestaurantsViewModel(private val repository: Repository): MainViewModel() {

	override fun getEntities() = getDataFromLocalSource()

	private fun getDataFromLocalSource() {
		localLiveData.value = AppState.Loading
		Thread {
			Thread.sleep(1000)
			localLiveData.postValue(AppState.Success(repository.getRestaurantFromLocalStorage()))
		}.start()
	}
}