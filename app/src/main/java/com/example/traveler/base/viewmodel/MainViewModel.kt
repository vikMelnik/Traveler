package com.example.traveler.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveler.base.AppState

abstract class MainViewModel() : ViewModel() {
	protected val localLiveData = MutableLiveData<AppState>()
	val liveData: LiveData<AppState> get() = localLiveData

	abstract fun getEntities()

}