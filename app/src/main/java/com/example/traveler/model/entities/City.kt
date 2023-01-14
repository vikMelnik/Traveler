package com.example.traveler.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(val nameCity: String = "Kyiv", val nameCountry: String = "Ukraine"): Parcelable
