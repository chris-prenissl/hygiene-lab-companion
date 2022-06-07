package com.christophprenissl.hygienecompanion.util

import com.christophprenissl.hygienecompanion.model.entity.*
import com.christophprenissl.hygienecompanion.presentation.util.createDates
import java.util.*

fun createCoveringLetterForSeries(
    id: String,
    description: String,
    coveringParametersCoveringLetter: List<Parameter> = emptyList(),
    labParametersCoveringLetter: List<Parameter> = emptyList(),
    sampleLocations: List<SampleLocation> = emptyList(),
    coveringSampleParameters: Map<ParameterBasis, Boolean> = emptyMap(),
    labSampleParameters: Map<ParameterBasis, Boolean> = emptyMap(),
    date: Date?
): CoveringLetter {
    return CoveringLetter(
        id = id,
        description = description,
        date = date,
        basicCoveringParameters = coveringParametersCoveringLetter,
        basicLabReportParameters = labParametersCoveringLetter,
        samples = sampleLocations.mapIndexed { idx, location ->
            val coveringSampleParametersSample = mutableListOf<Parameter>()
            coveringSampleParameters.forEach {
                if (it.value) {
                    coveringSampleParametersSample.add(
                        Parameter(
                            name = it.key.name,
                            value = "",
                            parameterType = it.key.parameterType
                        )
                    )
                }
            }
            val labSampleParametersSample = mutableListOf<Parameter>()
            labSampleParameters.forEach {
                if (it.value) {
                    labSampleParametersSample.add(
                        Parameter(
                            name = it.key.name,
                            value = "",
                            parameterType = it.key.parameterType
                        )
                    )
                }
            }
            Sample(
                id = idx.toString(),
                coveringSampleParameters = coveringSampleParametersSample,
                labSampleParameters = labSampleParametersSample,
                sampleLocation = location
            )
        },
        samplingState = SamplingState.Created
    )
}

fun ParameterBasis.mapToBlankValue(): Parameter {
    return Parameter(
        name = name,
        parameterType = parameterType,
        value = "")
}

fun Map<ParameterBasis, Boolean>.createParameterList(): List<Parameter> {
    val parameters = mutableListOf<Parameter>()
    forEach {
        if (it.value) {
            parameters.add(
                it.key.mapToBlankValue()
            )
        }
    }
    return parameters
}

fun createCoveringLettersForSeries(
    description: String?,
    coveringParameterBasesCoveringLetter: Map<ParameterBasis, Boolean> = emptyMap(),
    labParameterBasesCoveringLetter: Map<ParameterBasis, Boolean> = emptyMap(),
    sampleLocations: List<SampleLocation> = emptyList(),
    coveringSampleParameters: Map<ParameterBasis, Boolean> = emptyMap(),
    labSampleParameters: Map<ParameterBasis, Boolean> = emptyMap(),
    startDate: Date,
    plannedEndDate: Date,
    samplingSeriesType: SamplingSeriesType
): List<CoveringLetter> {
    val coveringLetterDates = createDates(startDate, plannedEndDate, samplingSeriesType)
    val coveringLetters = coveringLetterDates.mapIndexed { dId, date ->
        val coveringParametersCoveringLetter = coveringParameterBasesCoveringLetter.createParameterList()
        val labParametersCoveringLetter = labParameterBasesCoveringLetter.createParameterList()
        createCoveringLetterForSeries(
            id = dId.toString(),
            description = description ?: "",
            coveringParametersCoveringLetter = coveringParametersCoveringLetter,
            labParametersCoveringLetter = labParametersCoveringLetter,
            sampleLocations = sampleLocations,
            coveringSampleParameters = coveringSampleParameters,
            labSampleParameters = labSampleParameters,
            date = date
        )
    }
    return coveringLetters
}

fun Parameter.toValue(): Any {
    return when(this.parameterType) {
        ParameterType.Bool -> this.value.toBoolean()
        ParameterType.Number, ParameterType.Temperature -> {
            try {
                this.value.toFloat()
            } catch(_: Exception) {
                -1.0f
            }
        }
        else -> this.value
    }
}
