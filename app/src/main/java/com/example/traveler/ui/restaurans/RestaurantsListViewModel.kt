package com.example.traveler.ui.restaurans

import com.example.traveler.model.AppState
import com.example.traveler.model.repository.Repository
import com.example.traveler.viewmodel.MainViewModel


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