package com.christophprenissl.hygienecompanion.presentation.view.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.MutableState
import java.util.*

fun openDatePickerDialog(
    context: Context,
    date: MutableState<Date>,
    onSet: ((Date) -> Unit)? = null
) {
    val currentCalendar = Calendar.getInstance()
    currentCalendar.timeInMillis = date.value.time
    val cYear = currentCalendar.get(Calendar.YEAR)
    val cMonth = currentCalendar.get(Calendar.MONTH)
    val cDay = currentCalendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            date.value = Date(calendar.timeInMillis)
            if (onSet != null) {
                onSet(date.value)
            }
        },
        cYear,
        cMonth,
        cDay
    )
    datePicker.show()
}
