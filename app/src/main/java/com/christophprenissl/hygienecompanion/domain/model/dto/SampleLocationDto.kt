package com.christophprenissl.hygienecompanion.domain.model.dto

import com.christophprenissl.hygienecompanion.domain.model.entity.Address

data class SampleLocationDto(
    var id: String? = null,
    val description: String? = null,
    val extraInfo: String? = null,
    val nextHeater: String? = null,
    val addressId: String? = null
)
