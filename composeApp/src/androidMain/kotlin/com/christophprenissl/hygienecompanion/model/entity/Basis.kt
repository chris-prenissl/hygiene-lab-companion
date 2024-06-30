package com.christophprenissl.hygienecompanion.model.entity

data class Basis(
    val norm: String = "",
    val description: String = "",
    val basicCoveringParameters: List<ParameterBasis> = emptyList(),
    val coveringSampleParameters: List<ParameterBasis> = emptyList(),
    val labSampleParameters: List<ParameterBasis> = emptyList(),
    val basicLabReportParameters: List<ParameterBasis> = emptyList(),
)
