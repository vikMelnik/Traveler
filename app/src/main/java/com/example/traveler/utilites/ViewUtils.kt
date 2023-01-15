
package com.example.traveler.utilites

import android.view.View

private const val ARG_COUNTRY = "country"
private const val ARG_CITY = "city"

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
