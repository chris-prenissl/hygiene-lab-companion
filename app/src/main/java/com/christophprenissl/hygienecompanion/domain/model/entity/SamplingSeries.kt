package com.christophprenissl.hygienecompanion.domain.model.entity

import java.util.*

data class SamplingSeries(
    val id: String? = null,
    val created: Date? = null,
    val resultToClient: Boolean? = null,
    val resultToTestingProperty: Boolean? = null,
    val costLocation: String? = null,
    val laboratoryId: String? = null,
    val bases: List<Basis>? = null,
    val client: Address? = null,
    val sampleAddress: Address? = null,
    val samplingCompany: Address? = null,
    val endedDate: Date? = null
)
