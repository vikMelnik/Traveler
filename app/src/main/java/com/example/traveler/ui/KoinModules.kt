package com.example.traveler.ui


import com.example.traveler.model.repository.Repository
import com.example.traveler.model.repository.RepositoryImpl
import com.example.traveler.viewmodel.MainViewModel
import com.example.traveler.viewmodel.RestaurantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
	single<Repository> { RepositoryImpl() }
		//View models
	//viewModel{MainViewModel()}
	viewModel<RestaurantsViewModel>{ RestaurantsViewModel(get()) }
	}

