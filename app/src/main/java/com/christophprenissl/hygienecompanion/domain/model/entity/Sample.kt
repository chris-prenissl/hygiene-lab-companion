package com.christophprenissl.hygienecompanion.domain.model.entity

import java.util.*

data class Sample(
    val id: String? = null,
    val created: Date? = null,
    val extraInfoSampling: String? = null,
    val extraInfoLaboratory: String? = null,
    val warningMessage: String? = null,
    val coveringSampleParameters: List<Parameter>? = null,
    val labSampleParameters: List<Parameter>? = null,
    val sampleLocation: SampleLocation? = null
)
