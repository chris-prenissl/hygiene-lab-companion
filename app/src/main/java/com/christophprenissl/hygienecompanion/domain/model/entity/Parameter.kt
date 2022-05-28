package com.christophprenissl.hygienecompanion.domain.model.entity

data class Parameter (
    val name: String? = null,
    var value: String? = null,
    val parameterType: ParameterType? = null
)
