package com.christophprenissl.hygienecompanion.domain.model.entity

import java.util.*

data class CoveringLetter(
    var id: String? = null,
    var seriesId: String? = null,
    val description: String? = null,
    val date: Date? = null,
    val lotId: String? = null,
    val laboratoryIn: Date? = null,
    val resultCreated: Date? = null,
    val coveringParameters: List<Parameter>? = null,
    val labParameters: List<Parameter>? = null,
    val sampler: User? = null,
    val labWorker: User? = null,
    val samples: List<Sample>? = null,
    val samplingState: SamplingState? = null
)
