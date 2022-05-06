package com.christophprenissl.hygienecompanion.presentation.util

fun getValidTemperature(text: String): String {
    val filteredChars = text.filterIndexed { index, c ->
        c in "0123456789" || (c == '.' && text.indexOf('.') == index)
    }
    return if(filteredChars.contains('.')) {
        val beforeDecimal = filteredChars.substringBefore('.')
        val afterDecimal = filteredChars.substringAfter('.')
        beforeDecimal.take(3) + "." + afterDecimal.take(2)
    } else {
        filteredChars.take(3) + ".0"
    }
}