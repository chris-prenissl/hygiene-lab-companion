package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.util.getValidTemperature
import com.christophprenissl.hygienecompanion.presentation.view.component.*
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.SampleEdit
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.ParameterEdit
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLetterDetailView(
    viewModel: CoveringLettersViewModel,
    navController: NavController
) {
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
                itemsIndexed(basicLabReportValues) { idx, pValue ->
                    ParameterEdit(
                        parameter = coveringLetter.basicLabReportParameters!![idx],
                        value = pValue,
                        onNumbEdit = { value ->
                            basicLabReportValues[idx] = value.filter {
                                it.isDigit()
                            }
                            coveringLetter.basicLabReportParameters[idx].value =
                                basicLabReportValues[idx].toInt()
                        },
                        onTempEdit = { value ->
                            basicLabReportValues[idx] = getValidTemperature(value)
                            coveringLetter.basicLabReportParameters[idx].value =
                                basicLabReportValues[idx].toFloat()
                        },
                        onBoolEdit = { value ->
                            basicLabReportValues[idx] = value.toString()
                            coveringLetter.basicLabReportParameters[idx].value = value
                        },
                        onNoteEdit = { value ->
                            basicLabReportValues[idx] = value
                            coveringLetter.basicLabReportParameters[idx].value = value
                        }
                    )
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
                itemsIndexed(basicCoveringValues) { idx, pValue ->
                    ParameterEdit(
                        parameter = coveringLetter.basicCoveringParameters!![idx],
                        value = pValue,
                        onNumbEdit = { value ->
                            basicCoveringValues[idx] = value.filter {
                                it.isDigit()
                            }
                            coveringLetter.basicCoveringParameters[idx].value =
                                basicCoveringValues[idx].toInt()
                        },
                        onTempEdit = { value ->
                            basicCoveringValues[idx] = getValidTemperature(value)
                            coveringLetter.basicCoveringParameters[idx].value =
                                basicCoveringValues[idx].toFloat()
                        },
                        onBoolEdit = { value ->
                            basicCoveringValues[idx] = value.toString()
                            coveringLetter.basicCoveringParameters[idx].value = value
                        },
                        onNoteEdit = { value ->
                            basicCoveringValues[idx] = value
                            coveringLetter.basicCoveringParameters[idx].value = value
                        }
                    )
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
                            viewModel.finishCoveringLetterInLab(coveringLetter)
                            navController.navigate(Screen.CoveringLetters.graphRoute) {
                                popUpTo(HOME_ROUTE) {
                                    inclusive = true
                                }
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
                            viewModel.giveCoveringLetterToLab(coveringLetter)
                            navController.navigate(Screen.CoveringLetters.graphRoute) {
                                popUpTo(HOME_ROUTE) {
                                    inclusive = true
                                }
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
                                viewModel.labProgress(
                                    coveringLetter = coveringLetter
                                )
                            }
                            else -> {
                                viewModel.sampleProgress(
                                    coveringLetter = coveringLetter
                                )
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
