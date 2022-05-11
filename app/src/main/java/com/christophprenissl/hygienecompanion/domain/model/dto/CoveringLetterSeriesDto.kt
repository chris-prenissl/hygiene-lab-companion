package com.christophprenissl.hygienecompanion.domain.model.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class CoveringLetterSeriesDto(
    @DocumentId
    val id: String? = null,
    @ServerTimestamp
    val created: Date? = null,
    val description: String? = null,
    val resultToClient: Boolean? = null,
    val resultToTestingProperty: Boolean? = null,
    val costLocation: String? = null,
    val laboratoryId: String? = null,
    val basesByNorm: List<String?>? = null,
    val client: AddressDto? = null,
    val sampleAddress: AddressDto? = null,
    val samplingCompany: AddressDto? = null,
    val coveringLetters: List<String>? = null,
    val plannedStart: Date? = null,
    val plannedEnd: Date? = null,
    val hasEnded: Boolean? = null,
    val endedDate: Date? = null,
    val samplingSeriesType: String? = null
)
