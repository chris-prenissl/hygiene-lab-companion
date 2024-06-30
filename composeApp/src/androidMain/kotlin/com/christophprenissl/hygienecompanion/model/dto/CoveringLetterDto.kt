package com.christophprenissl.hygienecompanion.model.dto

import com.google.firebase.firestore.DocumentId
import java.util.*

data class CoveringLetterDto(
    @DocumentId
    val id: String? = null,
    var seriesId: String? = null,
    val description: String? = null,
    val date: Date? = null,
    val lotId: String? = null,
    val laboratoryIn: Date? = null,
    val resultCreated: Date? = null,
    val basicCoveringParameters: List<ParameterDto>? = null,
    val basicLabReportParameters: List<ParameterDto>? = null,
    var sampler: UserDto? = null,
    var labWorker: UserDto? = null,
    val samples: List<SampleDto>? = null,
    val samplingState: String? = null,
)
