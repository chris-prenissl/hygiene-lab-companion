package com.christophprenissl.hygienecompanion.domain.model

data class SampleLocation(
    val id: String? = null,
    val description: String? = null,
    val extraInfo: String? = null,
    val nextHeater: String? = null,
    val address: Address? = null
)
