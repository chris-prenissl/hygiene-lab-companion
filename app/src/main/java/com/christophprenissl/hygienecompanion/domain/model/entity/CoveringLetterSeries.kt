package com.christophprenissl.hygienecompanion.domain.model.entity

import java.util.*

data class CoveringLetterSeries(
    val id: String? = null,
    val created: Date? = null,
    val description: String? = null,
    val resultToClient: Boolean? = null,
    val resultToTestingProperty: Boolean? = null,
    val costLocation: String? = null,
    val laboratoryId: String? = null,
    val bases: List<Basis>? = null,
    val client: Address? = null,
    val sampleAddress: Address? = null,
    val samplingCompany: Address? = null,
    val coveringLetters: List<CoveringLetter>? = null,
    val hasEnded: Boolean? = null,
    val endedDate: Date? = null,
    val samplingSeriesType: SamplingSeriesType? = null
)
