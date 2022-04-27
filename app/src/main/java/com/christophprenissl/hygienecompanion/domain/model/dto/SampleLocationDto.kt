package com.christophprenissl.hygienecompanion.domain.model.dto

import com.google.firebase.firestore.DocumentId

data class SampleLocationDto(
    @DocumentId
    var id: String? = null,
    val description: String? = null,
    val extraInfo: String? = null,
    val nextHeater: String? = null,
    val addressId: String? = null
)
