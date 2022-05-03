package com.christophprenissl.hygienecompanion.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.dayMonthYearString(): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY)
    return formatter.format(this.time)
}
