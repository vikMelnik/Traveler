package com.example.traveler.ui


import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.model.repository.Repository
import com.example.traveler.model.repository.RepositoryImpl
import com.example.traveler.restaurants.viewmodel.RestaurantsListViewModel
import com.example.traveler.restaurants.viewmodel.RestaurantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	single<Repository> { RepositoryImpl<List<Restaurants>>() } //??????
	single<Repository> { RepositoryImpl<Restaurants>() }
		//View models
	//viewModel{MainViewModel()}
	viewModel<RestaurantsViewModel>{ RestaurantsViewModel(get()) }
	viewModel<RestaurantsListViewModel>{ RestaurantsListViewModel(get()) } //????????
	}

