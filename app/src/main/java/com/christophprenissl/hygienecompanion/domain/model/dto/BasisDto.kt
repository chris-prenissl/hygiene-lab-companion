package com.christophprenissl.hygienecompanion.domain.model.dto

import com.google.firebase.firestore.DocumentId

data class BasisDto(
    @DocumentId
    val norm: String? = null,
    val description: String? = null,
    val coveringParameters: HashMap<String, String>? = null,
    val coveringSampleParameters: HashMap<String, String>? = null,
    val labSampleParameters: HashMap<String, String>? = null,
    val labReportParameters: HashMap<String, String>? = null
)
