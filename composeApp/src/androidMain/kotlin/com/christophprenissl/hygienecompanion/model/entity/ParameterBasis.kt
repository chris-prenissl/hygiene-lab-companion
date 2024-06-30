package com.christophprenissl.hygienecompanion.model.entity

data class ParameterBasis(
    val creationId: Int = -1,
    var name: String,
    var parameterType: ParameterType,
)
