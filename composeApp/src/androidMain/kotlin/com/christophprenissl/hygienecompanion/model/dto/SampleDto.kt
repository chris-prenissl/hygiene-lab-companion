package com.christophprenissl.hygienecompanion.model.dto

import java.util.*

data class SampleDto(
    val id: String? = null,
    val created: Date? = null,
    val extraInfoSampling: String? = null,
    val extraInfoLaboratory: String? = null,
    val warningMessage: String? = null,
    val coveringSampleParameters: List<ParameterDto>? = null,
    val labSampleParameters: List<ParameterDto>? = null,
    val sampleLocation: SampleLocationDto? = null,
)
