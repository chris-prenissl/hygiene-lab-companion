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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.*
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.ParameterBooleanEdit
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.SampleEdit
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidNumberTextFieldValue
import com.christophprenissl.hygienecompanion.presentation.view.util.getValidTemperatureTextFieldValue
import com.christophprenissl.hygienecompanion.util.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLetterDetailView(
    viewModel: CoveringLettersViewModel = koinViewModel(),
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current

    val user = viewModel.userFlow.collectAsState(initial = null)
    val coveringLetter = viewModel.chosenCoveringLetter.value!!
    val title = coveringLetter.description
    val date = coveringLetter.date

    val samplingState by remember { mutableStateOf(coveringLetter.samplingState) }
    var lotId by remember { mutableStateOf(coveringLetter.lotId) }

    val basicCoveringValues = remember {
        coveringLetter.basicCoveringParameters.map {
            it.value
        }.toMutableStateList()
    }
    val basicLabReportValues = remember {
        coveringLetter.basicLabReportParameters.map {
            it.value
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
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        item {
            ParameterText(
                title = PLANNED_START_DATE,
                value = date?.dayMonthYearString()
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
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
                        title = coveringLetter.basicCoveringParameters[idx].name,
                        value = pValue
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                }
                itemsIndexed(basicLabReportValues) { idx, _ ->
                    val parameter = coveringLetter.basicCoveringParameters[idx]
                    when (parameter.parameterType) {
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
                                parameterName = parameter.name,
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
                        value = lotId,
                        onValueChange = {
                            lotId = it
                            coveringLetter.lotId = it
                        }
                    )
                }
                itemsIndexed(basicCoveringValues) { idx, _ ->
                    val parameter = coveringLetter.basicCoveringParameters[idx]
                    when (parameter.parameterType) {
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
                                parameterName = parameter.name,
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
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        item {
            LazyRow{
                items(coveringLetter.samples) { sample ->
                    samplingState?.let {
                        SampleEdit(
                            samplingState = it,
                            sample = sample
                        )
                    }
                }
            }
        }

        item {
            when(samplingState) {
                SamplingState.InLaboratory, SamplingState.LabInProgress -> {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.padding(vertical = standardPadding.dp),
                        onClick = {
                            viewModel.rejectCoveringLetter(coveringLetter)
                            Toast.makeText(context, SUCCESS_REJECT, Toast.LENGTH_SHORT)
                                .show()
                            onNavigateUp()
                        }
                    ) {
                        Text(GIVE_BACK_COVERING_LETTER)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = standardPadding.dp),
                        onClick = {
                            if (user.value != null) {
                                viewModel.finishCoveringLetterInLab(
                                    labWorker = user.value!!,
                                    coveringLetter = coveringLetter
                                )
                                Toast.makeText(context, SUCCESS_REPORT, Toast.LENGTH_SHORT)
                                    .show()
                                onNavigateUp()
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
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = standardPadding.dp),
                        onClick = {
                            if (user.value != null) {
                                viewModel.giveCoveringLetterToLab(
                                    sampler = user.value!!,
                                    coveringLetter = coveringLetter
                                )
                                Toast.makeText(context, SUCCESS_GIVE_TO_LAB, Toast.LENGTH_SHORT)
                                    .show()
                                onNavigateUp()
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
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }

        item {
            Button(
                onClick = {
                    coveringLetter.seriesId.let {
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
