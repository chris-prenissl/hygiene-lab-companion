package com.christophprenissl.hygienecompanion.domain.model.dto

import com.google.firebase.firestore.DocumentId

data class AddressDto(
    @DocumentId
    var id: String? = null,
    var name: String? = null,
    val zip: String? = null,
    val city: String? = null,
    val street: String? = null
)
