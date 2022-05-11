package com.christophprenissl.hygienecompanion.domain.model.entity

import java.util.*

data class Sample(
    var id: String? = null,
    val created: Date? = null,
    var extraInfoSampling: String? = null,
    var extraInfoLaboratory: String? = null,
    var warningMessage: String? = null,
    val coveringSampleParameters: List<Parameter>? = null,
    val labSampleParameters: List<Parameter>? = null,
    var sampleLocation: SampleLocation? = null
)
