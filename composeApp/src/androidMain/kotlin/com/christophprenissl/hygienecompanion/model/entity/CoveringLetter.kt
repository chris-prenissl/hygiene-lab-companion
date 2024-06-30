package com.christophprenissl.hygienecompanion.model.entity

import java.util.*

data class CoveringLetter(
    var id: String = "",
    var seriesId: String = "",
    val description: String = "",
    var date: Date? = null,
    var lotId: String = "",
    var laboratoryIn: Date? = null,
    var resultCreated: Date? = null,
    val basicCoveringParameters: List<Parameter> = emptyList(),
    val basicLabReportParameters: List<Parameter> = emptyList(),
    var sampler: User? = null,
    var labWorker: User? = null,
    val samples: List<Sample> = emptyList(),
    var samplingState: SamplingState? = null,
)
