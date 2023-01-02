package com.example.traveler.ui


import com.example.traveler.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
		//View models
	viewModel{MainViewModel()}
	}

