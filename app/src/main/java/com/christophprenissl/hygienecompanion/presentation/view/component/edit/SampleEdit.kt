package com.christophprenissl.hygienecompanion.presentation.view.component.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidNumberTextFieldValue
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidTemperatureTextFieldValue
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun SampleEdit(
    sample: Sample,
    samplingState: SamplingState
) {
    var extraInfoSampling by remember {
        mutableStateOf(
            TextFieldValue(sample.extraInfoSampling ?: EMPTY)
        )
    }
    var extraInfoLaboratory by remember {
        mutableStateOf(
            TextFieldValue(sample.extraInfoLaboratory ?: EMPTY)
        )
    }
    var warningMessage by remember { mutableStateOf(TextFieldValue(sample.warningMessage ?: EMPTY)) }

    val coveringSampleValues = remember {
        sample.coveringSampleParameters!!.map {
            it.value.toString()
        }.toMutableStateList()
    }
    val labSampleValues = remember {
        sample.labSampleParameters!!.map {
            it.value.toString()
        }.toMutableStateList()
    }

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(standardPadding),
        elevation = standardElevation
    ) {
        Column(
            modifier = Modifier.padding(standardPadding)
        ) {
            ParameterText(
                title = SAMPLE_LOCATION,
                value = sample.sampleLocation?.description
            )
            ParameterText(
                title = SAMPLE_ID,
                value = sample.id
            )
            ParameterText(
                title = SAMPLING_DATE,
                value = sample.created?.dayMonthYearString()
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))

            when(samplingState) {
                SamplingState.InLaboratory, SamplingState.LabInProgress -> {
                    ParameterText(
                        title = EXTRA_INFO_SAMPLING_PERSON,
                        value = sample.extraInfoSampling
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    ParameterText(
                        title = WARNING,
                        value = sample.warningMessage
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                }
                else -> {
                    ParameterTextField(
                        labelText = EXTRA_INFO_SAMPLING_PERSON,
                        value = extraInfoSampling,
                        onValueChange = {
                            extraInfoSampling = it
                            sample.extraInfoSampling = it.text
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    ParameterTextField(
                        labelText = WARNING,
                        value = warningMessage,
                        onValueChange = {
                            warningMessage = it
                            sample.warningMessage = it.text
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                }
            }

            when (samplingState) {
                SamplingState.InLaboratory, SamplingState.LabInProgress -> {
                    Text(COVERING_SAMPLE_PARAMETERS)
                    sample.coveringSampleParameters?.forEach {
                        ParameterText(
                            title = it.name?: EMPTY,
                            value = it.value.toString()
                        )
                    }
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    ParameterTextField(
                        labelText = EXTRA_INFO_LAB_PERSON,
                        value = extraInfoLaboratory,
                        onValueChange = {
                            extraInfoLaboratory = it
                            sample.extraInfoLaboratory = it.text
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    sample.labSampleParameters?.let { parameters ->
                        ParametersEditList(
                            title = LAB_SAMPLE_PARAMETERS,
                            parameters = parameters,
                            values = labSampleValues,
                            onNumbEdit = { idx, value ->
                                labSampleValues[idx] = getValidNumberTextFieldValue(value, labSampleValues[idx])
                                sample.labSampleParameters[idx].value =
                                    labSampleValues[idx].toInt()
                            },
                            onTempEdit = { idx, value ->
                                labSampleValues[idx] = getValidTemperatureTextFieldValue(value)
                                sample.labSampleParameters[idx].value =
                                    labSampleValues[idx].toFloat()
                            },
                            onBoolEdit = { idx, value ->
                                labSampleValues[idx] = value.toString()
                                sample.labSampleParameters[idx].value = value
                            },
                            onNoteEdit = { idx, value ->
                                labSampleValues[idx] = value
                                sample.labSampleParameters[idx].value = value
                            }
                        )
                    }
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                }
                SamplingState.LaboratoryResult -> {
                    Text(LAB_REPORT_AVAILABLE)
                }
                else -> {
                    sample.coveringSampleParameters?.let { parameters ->
                        ParametersEditList(
                            title = COVERING_SAMPLE_PARAMETERS,
                            parameters = parameters,
                            values = coveringSampleValues,
                            onNumbEdit = { idx, value ->
                                coveringSampleValues[idx] = getValidNumberTextFieldValue(value, coveringSampleValues[idx])
                                sample.coveringSampleParameters[idx].value =
                                    coveringSampleValues[idx].toInt()
                            },
                            onTempEdit = { idx, value ->
                                coveringSampleValues[idx] = getValidTemperatureTextFieldValue(value)
                                sample.coveringSampleParameters[idx].value =
                                    coveringSampleValues[idx].toFloat()
                            },
                            onBoolEdit = { idx, value ->
                                coveringSampleValues[idx] = value.toString()
                                sample.coveringSampleParameters[idx].value = value
                            },
                            onNoteEdit = { idx, value ->
                                coveringSampleValues[idx] = value
                                sample.coveringSampleParameters[idx].value = value
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
    }
}
