package com.example.traveler.model.entities.listEntities

import com.example.traveler.R
import com.example.traveler.model.entities.City

open class Entities {}
fun getDefaultCity(): City = City("Kyiv", "Ukraine")

fun  getUkrCityRestaurant() = listOf(
	Restaurants(City("Kyiv","Ukr"),"Borisfen",
		R.drawable.foodrestr, 5.5, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Zaporizhzhya","Ukr"),"Borisfen",
		R.drawable.picture, 5.4,"11:00 - 22:00","It was tasty food"),
	Restaurants(City("Kharkiv","Ukr"),"Borisfen",
		R.drawable.foodrestr, 5.8,"11:00 - 21:00","It was tasty food"),
	Restaurants(City("Donetsk","Ukr"),"Borisfen",
		R.drawable.foodrestr, 3.5, "11:00 - 23:00","It was tasty food"),
	Restaurants(City("Odesa","Ukr"),"Borisfen",
		R.drawable.foodrestr, 5.7, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Dnipro","Ukr"),"Borisfen",
		R.drawable.foodrestr, 4.5, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Kryvyi Rih","Ukr"),"Borisfen",
		R.drawable.foodrestr, 5.9, "11:00 - 20:00","It was tasty food"),
	Restaurants(City("Kryvyi Rih","Ukr"),"Borisfen",
		R.drawable.foodrestr, 5.7, "10:00 - 22:00","It was tasty food"),
	Restaurants(City("Melitopol","Ukr"),"Borisfen",
		R.drawable.foodrestr, 3.5, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Ivano-Frankivsk","Ukr"),"Borisfen",
		R.drawable.foodrestr, 5.6, "11:00 - 22:00","It was tasty food")
)
fun getWorldCityRestaurant() = listOf(
	Restaurants(City("Warsaw", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.9, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Krakow", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.7, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Lodz", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.2, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Gdansk", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.8, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Poznan", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.4, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Wroclaw", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.3, "11:00 - 22:00","It was tasty food"),
	Restaurants(City("Poznan", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.7, "11:00 - 21:00","It was tasty food"),
	Restaurants(City("Gdansk", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.4, "12:00 - 22:00","It was tasty food"),
	Restaurants(City("Lodz", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.9, "12:00 - 21:00","It was tasty food"),
	Restaurants(City("Warsaw", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.6, "11:00 - 21:00","It was tasty food"),
	Restaurants(City("Gdansk", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.2, "12:00 - 22:00","It was tasty food"),
	Restaurants(City("Poznan", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.8, "11:00 - 21:00","It was tasty food"),
	Restaurants(City("Wroclaw", "Poland"),"Borisfen",
		R.drawable.foodrestr, 4.5, "12:00 - 22:00","It was tasty food"),
	Restaurants(City("Warsaw", "Poland"),"Borisfen",
		R.drawable.foodrestr, 5.7, "10:00 - 22:00","It was tasty food"),
)