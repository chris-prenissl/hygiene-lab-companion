package com.christophprenissl.hygienecompanion.model.dto

import com.google.firebase.firestore.DocumentId

data class BasisDto(
    @DocumentId
    val norm: String? = null,
    val description: String? = null,
    val basicCoveringParameters: List<ParameterBasisDto>? = null,
    val coveringSampleParameters: List<ParameterBasisDto>? = null,
    val labSampleParameters: List<ParameterBasisDto>? = null,
    val basicLabReportParameters: List<ParameterBasisDto>? = null,
)
