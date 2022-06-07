package com.christophprenissl.hygienecompanion.model.entity

data class SampleLocation(
    val id: String = "",
    val description: String = "",
    val extraInfo: String = "",
    val nextHeater: String = "",
    val address: Address?
)
