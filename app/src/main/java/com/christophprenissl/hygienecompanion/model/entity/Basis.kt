package com.christophprenissl.hygienecompanion.model.entity

data class Basis(
    val norm: String? = null,
    val description: String? = null,
    val basicCoveringParameters: List<ParameterBasis>? = null,
    val coveringSampleParameters: List<ParameterBasis>? = null,
    val labSampleParameters: List<ParameterBasis>? = null,
    val basicLabReportParameters: List<ParameterBasis>? = null
)
