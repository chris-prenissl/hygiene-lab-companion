package com.christophprenissl.hygienecompanion.presentation.view.util

import org.junit.Test

class TextFieldHelperKtTest {

    @Test
    fun getValidNumberTextFieldValueBlank() {

        val oldValue = ""
        val zeroZero = " "

        val result = getValidNumberTextFieldValue(
            value = zeroZero,
            oldValue = oldValue)

        assert(result == "")
    }

    @Test
    fun getValidNumberTextFieldValueCommaFirst() {

        val oldValue = "12"
        val zeroZero = ".12"

        val result = getValidNumberTextFieldValue(
            value = zeroZero,
            oldValue = oldValue)

        assert(result == ".12")
    }

    @Test
    fun getValidNumberTextFieldValueZeroZero() {

        val oldValue = "0"
        val zeroZero = "00"

        val result = getValidNumberTextFieldValue(
            value = zeroZero,
            oldValue = oldValue)

        assert(result == "0")
    }

    @Test
    fun getValidNumberTextFieldValueZero() {

        val oldValue = ""
        val zero = "0"

        val result = getValidNumberTextFieldValue(
            value = zero,
            oldValue = oldValue)

        assert(result == zero)
    }

    @Test
    fun getValidNumberTextFieldValuePrefixedZeros() {

        val oldValue = "102010"
        val prefixedZero = "0102010"

        val result = getValidNumberTextFieldValue(
            value = prefixedZero,
            oldValue = oldValue)

        assert(result == oldValue)
    }

    @Test
    fun getValidNumberTextFieldValue0Point() {

        val oldValue = "0."
        val point = "."

        val result = getValidNumberTextFieldValue(
            value = point,
            oldValue = oldValue)

        assert(result == ".")
    }

    @Test
    fun getValidNumberTextFieldValue1Comma() {

        val oldValue = "1"
        val prefixedZero = "1,"

        val result = getValidNumberTextFieldValue(
            value = prefixedZero,
            oldValue = oldValue)

        assert(result == "1.")
    }

    @Test
    fun getValidNumberTextFieldValuePrefixedZerosAndComma() {
        val oldValue = "102010"
        val prefixedZero = "010,2010"

        val result = getValidNumberTextFieldValue(
            value = prefixedZero,
            oldValue = oldValue)

        assert(result == "10.2010")
    }

    @Test
    fun getValidNumberTextFieldValueDoubleCommas() {
        val oldValue = "10.2010"
        val doubleComma = "10.20,10"

        val result = getValidNumberTextFieldValue(
            value = doubleComma,
            oldValue = oldValue)

        assert(result == "10.2010")
    }

    @Test
    fun getValidNumberTextFieldValueInvalidChar() {
        val oldValue = "102010"
        val prefixedZero = "1020a10"

        val result = getValidNumberTextFieldValue(
            value = prefixedZero,
            oldValue = oldValue)

        assert(result == "102010")
    }

    @Test
    fun getValidTemperatureTextFieldValueTooBigPrefix() {

        val oldValue = "102.01"
        val newValue = "1002.01"

        val result = getValidTemperatureTextFieldValue(
            value = newValue,
            oldValue = oldValue
        )

        assert(result == oldValue)
    }

    @Test
    fun getValidTemperatureTextFieldValueCommaRemove() {

        val oldValue = "102."
        val newValue = "102"

        val result = getValidTemperatureTextFieldValue(
            value = newValue,
            oldValue = oldValue
        )

        assert(result == newValue)
    }

    @Test
    fun getValidTemperatureTextFieldValue1Remove() {

        val oldValue = "1"
        val newValue = ""

        val result = getValidTemperatureTextFieldValue(
            value = newValue,
            oldValue = oldValue
        )

        assert(result == "")
    }

    @Test
    fun getValidTemperatureTextFieldValue3PrefixComma() {

        val oldValue = "123."
        val newValue = "123.4"

        val result = getValidTemperatureTextFieldValue(
            value = newValue,
            oldValue = oldValue
        )

        assert(result == newValue)
    }

    @Test
    fun getValidTemperatureTextFieldValue3PrefixAndThenComma() {

        val oldValue = "123"
        val newValue = "123."

        val result = getValidTemperatureTextFieldValue(
            value = newValue,
            oldValue = oldValue
        )

        assert(result == newValue)
    }
}
