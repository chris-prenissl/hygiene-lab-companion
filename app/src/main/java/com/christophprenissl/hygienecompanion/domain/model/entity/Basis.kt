package com.christophprenissl.hygienecompanion.domain.model.entity

data class Basis(
    val norm: String? = null,
    val description: String? = null,
    val coveringParameters: HashMap<String, ParameterType>? = null,
    val coveringSampleParameters: HashMap<String, ParameterType>? = null,
    val labSampleParameters: HashMap<String, ParameterType>? = null,
    val labReportParameters: HashMap<String, ParameterType>? = null
)
