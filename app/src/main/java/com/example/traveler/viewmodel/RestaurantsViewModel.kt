package com.example.traveler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.traveler.model.AppState
import com.example.traveler.model.repository.Repository


class RestaurantsViewModel(private val repository: Repository): MainViewModel() {
	private val localLiveData = MutableLiveData<AppState>()
	val liveData: LiveData<AppState> get() = localLiveData

	fun getRestaurant() = getDataFromLocalSource()

	private fun getDataFromLocalSource() {
		localLiveData.value = AppState.Loading
		Thread {
			Thread.sleep(3000)
			localLiveData.postValue(AppState.Success(repository.getEntitiesFromLocalStorage()))
		}.start()
	}
}