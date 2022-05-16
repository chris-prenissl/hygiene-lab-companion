package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.util.getValidTemperature
import com.christophprenissl.hygienecompanion.util.standardPadding
import com.google.firebase.Timestamp

@Composable
fun SampleEditCard(
    sample: Sample,
    samplingState: SamplingState
) {
    var extraInfoSampling by remember {
        mutableStateOf(
            TextFieldValue(
                sample.extraInfoSampling ?: ""
            )
        )
    }
    var extraInfoLaboratory by remember {
        mutableStateOf(
            TextFieldValue(
                sample.extraInfoLaboratory ?: ""
            )
        )
    }
    var warningMessage by remember { mutableStateOf(TextFieldValue(sample.warningMessage ?: "")) }

    val coveringSampleValues = remember { mutableStateListOf<String>() }
    val labSampleValues = remember { mutableStateListOf<String>() }

    sample.coveringSampleParameters?.let { parameters ->
        parameters.forEach { parameter ->
            parameter.value.let {
                coveringSampleValues.add(it.toString())
            }
        }
    }
    sample.labSampleParameters?.let { parameters ->
        parameters.forEach { parameter ->
            parameter.value.let {
                labSampleValues.add(it.toString())
            }
        }
    }


    Column {
        Row {
            Text("Probeentnahme-Stelle")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.sampleLocation?.description?.let { Text(it) }
        }
        Row {
            Text("Probennummer")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.id?.let { Text(it) }
        }
        Row {
            Text("Datum")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.created?.let { Text(it.dayMonthYearString()) }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        Text("Zusatzinfo der probenentnehmenden Person")
        OutlinedTextField(
            value = extraInfoSampling,
            onValueChange = {
                extraInfoSampling = it
                sample.extraInfoSampling = it.text
            }
        )
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text("Zusatzinfo Laborarbeiter:in")
        OutlinedTextField(
            value = extraInfoLaboratory,
            onValueChange = {
                extraInfoLaboratory = it
                sample.extraInfoLaboratory = it.text
            }
        )
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        Text("Warnung")
        OutlinedTextField(
            value = warningMessage,
            onValueChange = {
                warningMessage = it
                sample.warningMessage = it.text
            }
        )
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        when (samplingState) {
            SamplingState.InLaboratory, SamplingState.LabInProgress -> {
                Text("Labor-Parameter")

                sample.labSampleParameters?.forEachIndexed { idx, parameter ->
                    when (parameter.parameterType) {
                        ParameterType.Note -> {
                            Row {
                                parameter.name?.let { Text(it) }
                                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                                OutlinedTextField(
                                    value = labSampleValues[idx], onValueChange = {
                                        labSampleValues[idx] = it
                                        sample.labSampleParameters[idx].value = it
                                    }
                                )
                            }
                        }
                        ParameterType.Number -> {
                            parameter.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            OutlinedTextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                value = labSampleValues[idx], onValueChange = { value ->
                                    labSampleValues[idx] = value.filter {
                                        it.isDigit()
                                    }
                                    sample.labSampleParameters[idx].value =
                                        labSampleValues[idx].toInt()
                                }
                            )
                        }
                        ParameterType.Temperature -> {
                            parameter.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            OutlinedTextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                value = labSampleValues[idx], onValueChange = { value ->
                                    labSampleValues[idx] = getValidTemperature(value)
                                    sample.labSampleParameters[idx].value =
                                        labSampleValues[idx].toFloat()
                                }
                            )
                        }
                        ParameterType.Bool -> {
                            parameter.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            Checkbox(
                                checked = labSampleValues[idx].toBoolean(),
                                onCheckedChange = {
                                    labSampleValues[idx] = it.toString()
                                    sample.labSampleParameters[idx].value = it
                                }
                            )
                        }
                        else -> Unit
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
            SamplingState.LaboratoryResult -> {
                Text("Laborergebnisse vorhanden")
            }
            else -> {
                Text("Probeentnahme-Parameter")
                sample.coveringSampleParameters?.forEachIndexed { idx, parameter ->
                    when (parameter.parameterType) {
                        ParameterType.Note -> {
                            Row {
                                parameter.name?.let { Text(it) }
                                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                                OutlinedTextField(
                                    value = coveringSampleValues[idx], onValueChange = {
                                        coveringSampleValues[idx] = it
                                        sample.coveringSampleParameters[idx].value = it
                                        sample.created = Timestamp.now().toDate()
                                    }
                                )
                            }
                        }
                        ParameterType.Number -> {
                            parameter.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            OutlinedTextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                value = coveringSampleValues[idx], onValueChange = { value ->
                                    coveringSampleValues[idx] = value.filter {
                                        it.isDigit()
                                    }
                                    sample.coveringSampleParameters[idx].value =
                                        coveringSampleValues[idx].toInt()
                                    sample.created = Timestamp.now().toDate()
                                }
                            )
                        }
                        ParameterType.Temperature -> {
                            parameter.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            OutlinedTextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                value = coveringSampleValues[idx], onValueChange = { value ->
                                    coveringSampleValues[idx] = getValidTemperature(value)
                                    sample.coveringSampleParameters[idx].value =
                                        coveringSampleValues[idx].toFloat()
                                }
                            )
                        }
                        ParameterType.Bool -> {
                            parameter.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            Checkbox(
                                checked = coveringSampleValues[idx].toBoolean(),
                                onCheckedChange = {
                                    coveringSampleValues[idx] = it.toString()
                                    sample.coveringSampleParameters[idx].value = it
                                    sample.created = Timestamp.now().toDate()
                                }
                            )
                        }
                        else -> Unit
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }
    }
}
