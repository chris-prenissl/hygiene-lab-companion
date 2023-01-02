package com.christophprenissl.hygienecompanion.model.entity

import java.util.*

data class CoveringLetterSeries(
    var id: String = "",
    val created: Date? = null,
    val description: String = "",
    val resultToClient: Boolean,
    val resultToTestingProperty: Boolean,
    val costLocation: String = "",
    val laboratoryId: String = "",
    val bases: List<Basis> = emptyList(),
    val client: Address? = null,
    val sampleAddress: Address? = null,
    val samplingCompany: Address? = null,
    val coveringLetters: List<CoveringLetter> = emptyList(),
    val plannedStart: Date,
    val plannedEnd: Date,
    val hasEnded: Boolean = false,
    val endedDate: Date? = null,
    val samplingSeriesType: SamplingSeriesType
)
