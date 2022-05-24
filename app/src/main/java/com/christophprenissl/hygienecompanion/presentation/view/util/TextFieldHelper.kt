package com.christophprenissl.hygienecompanion.presentation.view.util

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
