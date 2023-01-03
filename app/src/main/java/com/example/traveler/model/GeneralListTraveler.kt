package com.example.traveler.model

import android.graphics.drawable.Drawable
import com.example.traveler.R


const val TYPE_HEADER = 0
const val TYPE_FOOD = 1
const val TYPE_LIFE = 2
const val TYPE_SERVICES = 3
const val TYPE_NAME = 4
data class GeneralListTraveler(val name: String = "GGG", val sign: Int = R.drawable.bg_earth, val type: Int = TYPE_FOOD)


