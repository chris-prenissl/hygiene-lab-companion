package com.christophprenissl.hygienecompanion.domain.model.entity

data class Parameter (
    val name: String? = null,
    var value: Any? = null,
    val parameterType: ParameterType? = null
)
