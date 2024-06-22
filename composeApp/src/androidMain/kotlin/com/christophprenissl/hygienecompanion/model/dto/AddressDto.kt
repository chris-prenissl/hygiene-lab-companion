package com.christophprenissl.hygienecompanion.model.dto

import com.google.firebase.firestore.DocumentId

data class AddressDto(
    @DocumentId
    var id: String? = null,
    var name: String? = null,
    val zip: String? = null,
    val city: String? = null,
    val contactName: String? = null,
    val street: String? = null,
    val phone: String? = null,
    val fax: String? = null,
    val eMail: String? = null
)
