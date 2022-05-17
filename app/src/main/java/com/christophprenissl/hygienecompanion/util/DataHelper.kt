package com.christophprenissl.hygienecompanion.util

import com.christophprenissl.hygienecompanion.domain.model.entity.*
import com.christophprenissl.hygienecompanion.presentation.util.createDates
import java.util.*

fun createCoveringLetterForSeries(
    id: Int,
    description: String?,
    coveringParametersCoveringLetter: List<Parameter>?,
    labParametersCoveringLetter: List<Parameter>?,
    sampleLocations: List<SampleLocation>?,
    coveringSampleParameters: Map<ParameterBasis, Boolean>?,
    labSampleParameters: Map<ParameterBasis, Boolean>?,
    date: Date
): CoveringLetter {
    return CoveringLetter(
        id = id.toString(),
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
                        value = it.key.parameterType?.toValue(),
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
                        value = it.key.parameterType?.toValue(),
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

fun ParameterBasis.mapToParameter(): Parameter {
    return Parameter(
        name = name,
        parameterType = parameterType,
        value = when(parameterType) {
            ParameterType.Bool -> false
            ParameterType.Note -> ""
            ParameterType.Number -> 0
            ParameterType.Temperature -> 25.0
            else -> ""
        }
    )
}

fun Map<ParameterBasis, Boolean>.createParameterList(): List<Parameter> {
    val parameters = mutableListOf<Parameter>()
    forEach {
        if (it.value) {
            parameters.add(
                it.key.mapToParameter()
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
            id = dId,
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

fun ParameterType.toValue(): Any {
    return when(this) {
        ParameterType.Bool -> false
        ParameterType.Temperature -> 0.0
        ParameterType.Number -> 0
        ParameterType.Note -> ""
    }
}
