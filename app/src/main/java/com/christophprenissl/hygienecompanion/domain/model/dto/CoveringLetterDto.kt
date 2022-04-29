package com.christophprenissl.hygienecompanion.domain.model.dto

import com.google.firebase.firestore.DocumentId
import java.util.*

data class CoveringLetterDto(
    @DocumentId
    val id: String? = null,
    val created: Date? = null,
    val lotId: String? = null,
    val laboratoryIn: Date? = null,
    val resultCreated: Date? = null,
    val coveringParameters: List<ParameterDto>? = null,
    val labParameters: List<ParameterDto>? = null,
    val samplerId: String? = null,
    val labWorkerId: String? = null,
    val samples: List<SampleDto>? = null,
    val samplingState: String? = null
)
