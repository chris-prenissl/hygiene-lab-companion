package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.*
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.ParameterBooleanEdit
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.SampleEdit
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidNumberTextFieldValue
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidTemperatureTextFieldValue
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLetterDetailView(
    viewModel: CoveringLettersViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val user = viewModel.userFlow.collectAsState(initial = null)
    val coveringLetter = viewModel.chosenCoveringLetter.value!!
    val title = coveringLetter.description ?: COVERING_LETTER
    val date = coveringLetter.date

    val samplingState by remember { mutableStateOf(coveringLetter.samplingState) }
    var lotId by remember { mutableStateOf(coveringLetter.lotId) }

    val basicCoveringValues = remember {
        coveringLetter.basicCoveringParameters!!.map {
            it.value.toString()
        }.toMutableStateList()
    }
    val basicLabReportValues = remember {
        coveringLetter.basicLabReportParameters!!.map {
            it.value.toString()
        }.toMutableStateList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            TitleText(title = title)
        }

        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterText(
                title = PLANNED_START_DATE,
                value = date?.dayMonthYearString()
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        when(samplingState) {
            SamplingState.LabInProgress, SamplingState.InLaboratory -> {
                item {
                    ParameterText(
                        title = LOT_ID,
                        value = lotId
                    )
                }
                itemsIndexed(basicCoveringValues) { idx, pValue ->
                    ParameterText(
                        title = coveringLetter.basicCoveringParameters!![idx].name.toString(),
                        value = pValue
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                }
                itemsIndexed(basicLabReportValues) { idx, _ ->
                    val parameter = coveringLetter.basicCoveringParameters!![idx]
                    when (parameter.parameterType!!) {
                        ParameterType.Temperature -> {
                            ParameterTextField(
                                labelText = parameter.name,
                                value = basicLabReportValues[idx],
                                onValueChange = {
                                    val input = getValidTemperatureTextFieldValue(it, basicLabReportValues[idx])
                                    parameter.value = input
                                    basicLabReportValues[idx] = input
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        ParameterType.Number -> {
                            ParameterTextField(
                                labelText = parameter.name,
                                value = basicLabReportValues[idx],
                                onValueChange = {
                                    val input = getValidNumberTextFieldValue(it, basicLabReportValues[idx])
                                    parameter.value = input
                                    basicLabReportValues[idx] = input
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        ParameterType.Note -> {
                            ParameterTextField(
                                labelText = parameter.name,
                                value = basicLabReportValues[idx],
                                onValueChange = { input ->
                                    parameter.value = input
                                    basicLabReportValues[idx] = input
                                }
                            )
                        }
                        ParameterType.Bool -> {
                            ParameterBooleanEdit(
                                parameterName = parameter.name?: EMPTY,
                                value = basicLabReportValues[idx],
                                onCheckedChange = {
                                    basicLabReportValues[idx] = it.toString()
                                    parameter.value = it.toString()
                                }
                            )
                        }
                    }
                }
            }
            else -> {
                item {
                    ParameterTextField(
                        labelText = LOT_ID,
                        value = lotId?: "",
                        onValueChange = {
                            lotId = it
                            coveringLetter.lotId = it
                        }
                    )
                }
                itemsIndexed(basicCoveringValues) { idx, _ ->
                    val parameter = coveringLetter.basicCoveringParameters!![idx]
                    when (parameter.parameterType!!) {
                        ParameterType.Temperature -> {
                            ParameterTextField(
                                labelText = parameter.name,
                                value = basicCoveringValues[idx],
                                onValueChange = {
                                    val input = getValidTemperatureTextFieldValue(it, basicCoveringValues[idx])
                                    parameter.value = input
                                    basicCoveringValues[idx] = input
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        ParameterType.Number -> {
                            ParameterTextField(
                                labelText = parameter.name,
                                value = basicCoveringValues[idx],
                                onValueChange = {
                                    val input = getValidNumberTextFieldValue(it, basicCoveringValues[idx])
                                    parameter.value = input
                                    basicCoveringValues[idx] = input
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        ParameterType.Note -> {
                            ParameterTextField(
                                labelText = parameter.name,
                                value = basicCoveringValues[idx],
                                onValueChange = { input ->
                                    parameter.value = input
                                    basicCoveringValues[idx] = input
                                }
                            )
                        }
                        ParameterType.Bool -> {
                            ParameterBooleanEdit(
                                parameterName = parameter.name?: EMPTY,
                                value = basicCoveringValues[idx],
                                onCheckedChange = {
                                    basicCoveringValues[idx] = it.toString()
                                    parameter.value = it.toString()
                                }
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            LazyRow{
                coveringLetter.samples?.let { samples ->
                    items(samples) { sample ->
                        samplingState?.let {
                            SampleEdit(
                                samplingState = it,
                                sample = sample
                            )
                        }
                    }
                }
            }
        }

        item {
            when(samplingState) {
                SamplingState.InLaboratory, SamplingState.LabInProgress -> {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        modifier = Modifier.padding(vertical = standardPadding),
                        onClick = {
                            viewModel.rejectCoveringLetter(coveringLetter)
                            Toast.makeText(context, SUCCESS_REJECT, Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate(Screen.CoveringLetters.graphRoute) {
                                popUpTo(HOME_ROUTE) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(GIVE_BACK_COVERING_LETTER)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = standardPadding),
                        onClick = {
                            if (user.value != null) {
                                viewModel.finishCoveringLetterInLab(
                                    labWorker = user.value!!,
                                    coveringLetter = coveringLetter
                                )
                                Toast.makeText(context, SUCCESS_REPORT, Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.CoveringLetters.graphRoute) {
                                    popUpTo(HOME_ROUTE) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Toast.makeText(context, NO_LAB_WORKER_REGISTERED, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    ) {
                        Text(END_REPORT)
                    }
                }
                else -> {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = standardPadding),
                        onClick = {
                            if (user.value != null) {
                                viewModel.giveCoveringLetterToLab(
                                    sampler = user.value!!,
                                    coveringLetter = coveringLetter
                                )
                                Toast.makeText(context, SUCCESS_GIVE_TO_LAB, Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.CoveringLetters.graphRoute) {
                                    popUpTo(HOME_ROUTE) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Toast.makeText(context, NO_SAMPLER_REGISTERED, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    ) {
                        Text(HAND_IN_COVERING_LETTER)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Button(
                onClick = {
                    coveringLetter.seriesId?.let {
                        when(samplingState) {
                            SamplingState.InLaboratory, SamplingState.LabInProgress -> {
                                if (user.value != null) {
                                    viewModel.labProgress(
                                        labWorker = user.value!!,
                                        coveringLetter = coveringLetter
                                    )
                                    Toast.makeText(context, SAVED, Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(context, NO_LAB_WORKER_REGISTERED, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            else -> {
                                if (user.value != null) {
                                    viewModel.sampleProgress(
                                        sampler = user.value!!,
                                        coveringLetter = coveringLetter
                                    )
                                    Toast.makeText(context, SAVED, Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(context, NO_SAMPLER_REGISTERED, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }
                }
            ) {
                Text(SAVE)
            }
        }
    }
}
