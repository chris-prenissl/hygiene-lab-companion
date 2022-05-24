package com.christophprenissl.hygienecompanion.domain.model.entity

import java.util.*

data class CoveringLetter(
    var id: String? = null,
    var seriesId: String? = null,
    val description: String? = null,
    var date: Date? = null,
    var lotId: String? = null,
    var laboratoryIn: Date? = null,
    var resultCreated: Date? = null,
    val basicCoveringParameters: List<Parameter>? = null,
    val basicLabReportParameters: List<Parameter>? = null,
    val sampler: User? = null,
    val labWorker: User? = null,
    val samples: List<Sample>? = null,
    var samplingState: SamplingState? = null
)
