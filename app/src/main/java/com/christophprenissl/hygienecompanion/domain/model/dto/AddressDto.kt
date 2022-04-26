package com.christophprenissl.hygienecompanion.domain.model.dto

data class AddressDto(
    var id: String? = null,
    var name: String? = null,
    val zip: String? = null,
    val city: String? = null,
    val street: String? = null,
    val extraInfo: String? = null
)
