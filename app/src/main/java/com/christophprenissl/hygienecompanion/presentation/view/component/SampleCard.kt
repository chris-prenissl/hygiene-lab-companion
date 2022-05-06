package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.util.getValidTemperature
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun SampleCard(sample: Sample) {
    var extraInfoSampling by remember { mutableStateOf(TextFieldValue("")) }
    var extraInfoLaboratory by remember { mutableStateOf(TextFieldValue("")) }
    var warning by remember { mutableStateOf(TextFieldValue("")) }

    val coveringSampleValues = remember { mutableStateListOf("") }

    sample.coveringSampleParameters?.let { parameters ->
        parameters.forEach { parameter ->
            parameter.value.let {
                coveringSampleValues.add(it.toString())
            }
        }
    }

    Column {
        Row {
            Text("Probennummer")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.id?.let { Text(it) }
        }
        Row {
            Text("Datum")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.created?.let { Text(it.dayMonthYearString()) }
        }
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        Text("Zusatzinfo der probenentnehmenden Person")
        OutlinedTextField(
            value = extraInfoSampling,
            onValueChange = {
                extraInfoSampling = it
            }
        )
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        Text("Zusatzinfo Laborarbeiter:in")
        OutlinedTextField(
            value = extraInfoLaboratory,
            onValueChange = {
                extraInfoLaboratory = it
            }
        )
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        Text("Warnung")
        OutlinedTextField(
            value = warning,
            onValueChange = {
                warning = it
            }
        )
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        Text("Probeentnahme-Parameter")
        sample.coveringSampleParameters?.forEachIndexed { idx, parameter ->
            when(parameter.parameterType) {
                ParameterType.Note -> {
                    Row {
                        parameter.name?.let { Text(it) }
                        Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                        OutlinedTextField(
                            value = coveringSampleValues[idx], onValueChange = {
                                coveringSampleValues[idx] = it
                            })
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
                        })
                }
                ParameterType.Temperature -> {
                    parameter.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = coveringSampleValues[idx], onValueChange = { value ->
                            coveringSampleValues[idx] = getValidTemperature(value)
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
                        }
                    )
                }
                else -> Unit
            }
        }
        Button(
            onClick = {
                sample.coveringSampleParameters?.forEachIndexed { idx, it ->
                    when(it.parameterType) {
                        ParameterType.Bool -> {
                            it.value = coveringSampleValues[idx].toBoolean()
                        }
                        ParameterType.Temperature -> {
                            it.value = coveringSampleValues[idx].toFloat()
                        }
                        ParameterType.Number -> {
                            it.value = coveringSampleValues[idx].toInt()
                        }
                        else -> {
                            it.value = coveringSampleValues[idx]
                        }
                    }
                }
            }
        ) {
            Text("Probeentnahme speichern")
        }
    }
}