package com.christophprenissl.hygienecompanion.presentation.view.component.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.christophprenissl.hygienecompanion.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.model.entity.Sample
import com.christophprenissl.hygienecompanion.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.util.checkIfNotEmptyAndNotCurrentDay
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidNumberTextFieldValue
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidTemperatureTextFieldValue
import com.christophprenissl.hygienecompanion.util.*
import com.google.firebase.Timestamp
import java.util.*

@Composable
fun SampleEdit(
    sample: Sample,
    samplingState: SamplingState
) {
    val currentDate by remember { mutableStateOf(Date()) }
    var date by remember { mutableStateOf(sample.created?.dayMonthYearString()) }
    var extraInfoSampling by remember {
        mutableStateOf(
            TextFieldValue(sample.extraInfoSampling ?: "")
        )
    }
    var extraInfoLaboratory by remember {
        mutableStateOf(
            TextFieldValue(sample.extraInfoLaboratory ?: "")
        )
    }
    var warningMessage by remember { mutableStateOf(TextFieldValue(sample.warningMessage ?: "")) }

    val coveringSampleValues = remember {
        sample.coveringSampleParameters!!.map {
            it.value.toString()
        }.toMutableStateList()
    }
    val labSampleValues = remember {
        sample.labSampleParameters!!.map {
            mutableStateOf(it.value.toString())
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
                value = date ?: EMPTY
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
                            if (checkIfNotEmptyAndNotCurrentDay(
                                    sample = sample,
                                    currentDate = currentDate,
                                    value = it.text
                                )) {
                                sample.created = Timestamp.now().toDate()
                                date = sample.created?.dayMonthYearString()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    ParameterTextField(
                        labelText = WARNING,
                        value = warningMessage,
                        onValueChange = {
                            warningMessage = it
                            sample.warningMessage = it.text
                            if (checkIfNotEmptyAndNotCurrentDay(
                                    sample = sample,
                                    currentDate = currentDate,
                                    value = it.text
                                )) {
                                sample.created = Timestamp.now().toDate()
                                date = sample.created?.dayMonthYearString()
                            }
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
                            if (checkIfNotEmptyAndNotCurrentDay(
                                    sample = sample,
                                    currentDate = currentDate,
                                    value = it.text
                                )) {
                                sample.created = Timestamp.now().toDate()
                                date = sample.created?.dayMonthYearString()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    sample.labSampleParameters?.let { parameters ->
                        parameters.forEachIndexed { idx, _ ->
                            val parameter = parameters[idx]
                            when (parameter.parameterType!!) {
                                ParameterType.Temperature -> {
                                    ParameterTextField(
                                        labelText = parameter.name,
                                        value = labSampleValues[idx].value,
                                        onValueChange = {
                                            val input = getValidTemperatureTextFieldValue(it, labSampleValues[idx].value)
                                            parameter.value = input
                                            labSampleValues[idx].value = input
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                }
                                ParameterType.Number -> {
                                    ParameterTextField(
                                        labelText = parameter.name,
                                        value = labSampleValues[idx].value,
                                        onValueChange = {
                                            val input = getValidNumberTextFieldValue(it, labSampleValues[idx].value)
                                            parameter.value = input
                                            labSampleValues[idx].value = input
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                }
                                ParameterType.Note -> {
                                    ParameterTextField(
                                        labelText = parameter.name,
                                        value = labSampleValues[idx].value,
                                        onValueChange = { input ->
                                            parameter.value = input
                                            labSampleValues[idx].value = input
                                        }
                                    )
                                }
                                ParameterType.Bool -> {
                                    ParameterBooleanEdit(
                                        parameterName = parameter.name?: EMPTY,
                                        value = labSampleValues[idx].value,
                                        onCheckedChange = {
                                            labSampleValues[idx].value = it.toString()
                                            parameter.value = it.toString()
                                        }
                                    )
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                }
                SamplingState.LaboratoryResult -> {
                    Text(LAB_REPORT_AVAILABLE)
                }
                else -> {
                    sample.coveringSampleParameters?.let { parameters ->
                        parameters.forEachIndexed { idx, _ ->
                            val parameter = parameters[idx]
                            when (parameter.parameterType!!) {
                                ParameterType.Temperature -> {
                                    ParameterTextField(
                                        labelText = parameter.name,
                                        value = coveringSampleValues[idx],
                                        onValueChange = {
                                            val input = getValidTemperatureTextFieldValue(it, coveringSampleValues[idx])
                                            parameter.value = input
                                            coveringSampleValues[idx] = input
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                }
                                ParameterType.Number -> {
                                    ParameterTextField(
                                        labelText = parameter.name,
                                        value = coveringSampleValues[idx],
                                        onValueChange = {
                                            val input = getValidNumberTextFieldValue(it, coveringSampleValues[idx])
                                            parameter.value = input
                                            coveringSampleValues[idx] = input
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                }
                                ParameterType.Note -> {
                                    ParameterTextField(
                                        labelText = parameter.name,
                                        value = coveringSampleValues[idx],
                                        onValueChange = { input ->
                                            parameter.value = input
                                            coveringSampleValues[idx] = input
                                        }
                                    )
                                }
                                ParameterType.Bool -> {
                                    ParameterBooleanEdit(
                                        parameterName = parameter.name?: EMPTY,
                                        value = coveringSampleValues[idx],
                                        onCheckedChange = {
                                            coveringSampleValues[idx] = it.toString()
                                            parameter.value = it.toString()
                                        }
                                    )
                                }
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
    }
}
