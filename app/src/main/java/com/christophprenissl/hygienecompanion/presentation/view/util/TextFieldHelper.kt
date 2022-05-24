package com.christophprenissl.hygienecompanion.presentation.view.util

import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.util.FALSE
import com.christophprenissl.hygienecompanion.util.TRUE
import java.util.*

fun getValidNumberTextFieldValue(newValue: String, oldValue: String): String {
    val removedWhenOnlyBlank = newValue.removePrefix(if (oldValue == "0" && newValue.isNotBlank()) oldValue else "")
    val notBlank = removedWhenOnlyBlank.ifBlank { "0" }
    return notBlank.filter { it.isDigit() }
}

fun getValidTemperatureTextFieldValue(newValue: String): String {
    val filteredChars = newValue.filterIndexed { index, c ->
        c in "0123456789" || (c == '.' && newValue.indexOf('.') == index)
    }
    return if(filteredChars.contains('.')) {
        val beforeDecimal = filteredChars.substringBefore('.').ifEmpty { "0" }
        val afterDecimal = filteredChars.substringAfter('.').ifEmpty { "0" }
        beforeDecimal.take(3) + "." + afterDecimal.take(2)
    } else {
        filteredChars.take(3) + ".0"
    }
}

fun checkIfNotEmptyAndNotCurrentDay(sample: Sample, currentDate: Date, value: String): Boolean {
    return sample.created?.dayMonthYearString() != currentDate.dayMonthYearString()
        && value.isNotBlank()
}

fun Boolean.translation(): String {
    return if (this) return TRUE else FALSE
}
