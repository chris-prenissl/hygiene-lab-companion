package com.christophprenissl.hygienecompanion.domain.model.entity

data class Basis(
    val norm: String? = null,
    val description: String? = null,
    val coveringParameters: List<ParameterBasis>? = null,
    val coveringSampleParameters: List<ParameterBasis>? = null,
    val labSampleParameters: List<ParameterBasis>? = null,
    val labReportParameters: List<ParameterBasis>? = null
)
