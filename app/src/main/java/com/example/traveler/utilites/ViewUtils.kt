
package com.example.traveler.utilites

import android.app.Application
import android.provider.Settings.Global.getString
import android.view.View
import com.example.traveler.R
import com.example.traveler.model.entities.GeneralListTraveler
import com.example.traveler.model.entities.TYPE_HEADER
import com.example.traveler.model.entities.TYPE_NAME
import com.example.traveler.startlist.StartListFragment
import com.example.traveler.ui.App
import kotlin.coroutines.coroutineContext

private const val ARG_COUNTRY = "country"
private const val ARG_CITY = "city"

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ghfhg(): ArrayList<GeneralListTraveler> {
    val listGeneral = arrayListOf<GeneralListTraveler>()

    for (i in StartListFragment().resources.getStringArray(R.array.foodList).toList()) {
        listGeneral.add(GeneralListTraveler(i, R.drawable.pngwing1, TYPE_NAME))
    }
    for (i in StartListFragment().resources.getStringArray(R.array.lifeList).toList()) {
        listGeneral.add(GeneralListTraveler(i, R.drawable.rest, TYPE_NAME))
    }
    for (i in StartListFragment().resources.getStringArray(R.array.servicesList).toList()) {
        listGeneral.add(GeneralListTraveler(i, R.drawable.cleaner, TYPE_NAME))
    }
    return listGeneral
}