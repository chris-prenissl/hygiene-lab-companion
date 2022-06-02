package com.christophprenissl.hygienecompanion.util

import com.christophprenissl.hygienecompanion.model.entity.*
import com.christophprenissl.hygienecompanion.presentation.util.createDates
import java.util.*

fun createCoveringLetterForSeries(
    id: String,
    description: String?,
    coveringParametersCoveringLetter: List<Parameter>?,
    labParametersCoveringLetter: List<Parameter>?,
    sampleLocations: List<SampleLocation>?,
    coveringSampleParameters: Map<ParameterBasis, Boolean>?,
    labSampleParameters: Map<ParameterBasis, Boolean>?,
    date: Date
): CoveringLetter {
    return CoveringLetter(
        id = id,
        description = description,
        date = date,
        basicCoveringParameters = coveringParametersCoveringLetter,
        basicLabReportParameters = labParametersCoveringLetter,
        samples = sampleLocations?.mapIndexed { idx, location ->
            val coveringSampleParametersSample = mutableListOf<Parameter>()
            coveringSampleParameters?.forEach {
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
            labSampleParameters?.forEach {
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
    coveringParameterBasesCoveringLetter: Map<ParameterBasis, Boolean>?,
    labParameterBasesCoveringLetter: Map<ParameterBasis, Boolean>?,
    sampleLocations: List<SampleLocation>?,
    coveringSampleParameters: Map<ParameterBasis, Boolean>?,
    labSampleParameters: Map<ParameterBasis, Boolean>?,
    startDate: Date,
    plannedEndDate: Date,
    samplingSeriesType: SamplingSeriesType
): List<CoveringLetter> {
    val coveringLetterDates = createDates(startDate, plannedEndDate, samplingSeriesType)
    val coveringLetters = coveringLetterDates.mapIndexed { dId, date ->
        val coveringParametersCoveringLetter = coveringParameterBasesCoveringLetter?.createParameterList()
        val labParametersCoveringLetter = labParameterBasesCoveringLetter?.createParameterList()
        createCoveringLetterForSeries(
            id = dId.toString(),
            description = description,
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
                this.value?.toFloat()?: -1.0f
            } catch(_: Exception) {
                -1.0f
            }
        }
        else -> this.value.toString()
    }
}
