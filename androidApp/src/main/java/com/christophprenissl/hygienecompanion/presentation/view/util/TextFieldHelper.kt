package com.christophprenissl.hygienecompanion.presentation.view.util

import com.christophprenissl.hygienecompanion.model.entity.Sample
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.util.FALSE
import com.christophprenissl.hygienecompanion.util.TRUE
import java.util.*

fun getValidNumberTextFieldValue(value: String, oldValue: String): String {
    val lettersFiltered = value.filter { it in "0123456789,." }
    val commaReplaced = lettersFiltered.replace(",", ".")
    val separatorCount = commaReplaced.count { it in "." }

    if (separatorCount > 1) return oldValue

    var lastPrefixedZeroIndex = -1

    run loop@{
        commaReplaced.forEachIndexed { i, c ->
            if (c == '.' && lastPrefixedZeroIndex == i-1) {
                lastPrefixedZeroIndex--
                return@loop
            }
            if (c !in "0") return@loop
            else lastPrefixedZeroIndex = i
        }
    }

    if (lastPrefixedZeroIndex <= -1) return commaReplaced
    if (lastPrefixedZeroIndex == commaReplaced.lastIndex) return "0"

    return commaReplaced.removeRange(0, lastPrefixedZeroIndex+1)
}

fun getValidTemperatureTextFieldValue(value: String, oldValue: String): String {
    val validNumber = getValidNumberTextFieldValue(value = value, oldValue = oldValue)
    val idxSeparator = validNumber.indexOf('.')
    if (idxSeparator > 3) {
        return oldValue
    } else if (validNumber.lastIndex - idxSeparator > 2 && idxSeparator >= 0) {
        return oldValue
    } else if (validNumber.lastIndex > 2 && idxSeparator == -1) {
        return oldValue
    }

    return validNumber
}

fun checkIfNotEmptyAndNotCurrentDay(sample: Sample, currentDate: Date, value: String): Boolean {
    return sample.created?.dayMonthYearString() != currentDate.dayMonthYearString()
        && value.isNotBlank()
}

fun Boolean.translation(): String {
    return if (this) return TRUE else FALSE
}
