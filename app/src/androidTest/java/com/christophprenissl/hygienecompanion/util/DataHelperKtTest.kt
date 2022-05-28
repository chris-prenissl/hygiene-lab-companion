package com.christophprenissl.hygienecompanion.util

import com.christophprenissl.hygienecompanion.domain.model.entity.Parameter
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import org.junit.Assert.*

import org.junit.Test

class DataHelperKtTest {

    @Test
    fun toValueNullNumber() {
        val parameter = Parameter(
            name = "test",
            value = null,
            parameterType = ParameterType.Number
        )

        val result = parameter.toValue()

        assert(result == -1.0f)
    }

    @Test
    fun toValueBlankNumber() {
        val parameter = Parameter(
            name = "test",
            value = "      ",
            parameterType = ParameterType.Number
        )

        val result = parameter.toValue()

        assert(result == -1.0f)
    }

    @Test
    fun toValueFalse() {
        val parameter = Parameter(
            name = "test",
            value = "false",
            parameterType = ParameterType.Bool
        )

        val result = parameter.toValue()

        assert(result == false)
    }

    @Test
    fun toValueNote() {
        val parameter = Parameter(
            name = "test",
            value = "false",
            parameterType = ParameterType.Note
        )

        val result = parameter.toValue()

        assert(result == "false")
    }
}