package com.example.traveler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveler.model.AppState

abstract class MainViewModel() : ViewModel() {
	protected val localLiveData = MutableLiveData<AppState>()
	val liveData: LiveData<AppState> get() = localLiveData

	abstract fun getEntities()

}