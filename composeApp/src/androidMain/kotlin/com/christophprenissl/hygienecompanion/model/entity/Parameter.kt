package com.christophprenissl.hygienecompanion.model.entity

data class Parameter(
    val name: String,
    var value: String = "",
    val parameterType: ParameterType,
)
