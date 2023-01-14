package com.example.traveler.restaurants.viewmodel

import com.example.traveler.base.AppState
import com.example.traveler.model.repository.Repository
import com.example.traveler.base.viewmodel.MainViewModel


class RestaurantsListViewModel(private val repository: Repository): MainViewModel() {

	override fun getEntities() = getDataFromLocalSource()

	private fun getDataFromLocalSource() {
		localLiveData.value = AppState.Loading
		Thread {
			Thread.sleep(3000)
			localLiveData.postValue(AppState.Success(repository.getRestaurantFromLocalStorageUkrCity()))
		}.start()
	}
}