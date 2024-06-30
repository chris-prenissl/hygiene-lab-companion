package com.christophprenissl.hygienecompanion.model.entity

import java.util.*

data class Sample(
    var id: String,
    var created: Date? = null,
    var extraInfoSampling: String = "",
    var extraInfoLaboratory: String = "",
    var warningMessage: String = "",
    val coveringSampleParameters: List<Parameter> = emptyList(),
    val labSampleParameters: List<Parameter> = emptyList(),
    var sampleLocation: SampleLocation,
)
