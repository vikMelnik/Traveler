package com.example.traveler.model.entities

import com.example.traveler.R

const val TYPE_HEADER = 0
const val TYPE_NAME = 4
data class GeneralListTraveler(val name: String = "GGG", val sign: Int = R.drawable.bg_earth, val type: Int = TYPE_NAME)


