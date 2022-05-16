package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.SampleEditCard
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun CoveringLetterDetailView(
    viewModel: CoveringLettersViewModel,
    navController: NavController
) {
    val coveringLetter = viewModel.chosenCoveringLetter.value
    val title = coveringLetter?.description ?: "Probe-Entnahme"
    val date = coveringLetter?.date

    val samplingState by remember { mutableStateOf(coveringLetter?.samplingState) }

    var lotId by remember { mutableStateOf(coveringLetter?.lotId) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(title)
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            Text("Geplante Abnahme: ${date?.dayMonthYearString()}")
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            Row {
                Text("Chargennummer")
                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                OutlinedTextField(
                    value = lotId?: "",
                    onValueChange = {
                        lotId = it
                        if (coveringLetter != null) {
                            coveringLetter.lotId = it
                        }
                    }
                )
            }
        }

        item {
            LazyRow{
                coveringLetter?.samples?.let { samples ->
                    items(samples) { sample ->
                        samplingState?.let {
                            SampleEditCard(
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
                            if (coveringLetter != null) {
                                viewModel.rejectCoveringLetter(coveringLetter)
                                navController.navigate(Screen.CoveringLetters.graphRoute)
                            }
                        }
                    ) {
                        Text("Probeentnahme zurückgeben")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = standardPadding),
                        onClick = {
                            if (coveringLetter != null) {
                                viewModel.finishCoveringLetterInLab(coveringLetter)
                                navController.navigate(Screen.CoveringLetters.graphRoute)
                            }
                        }
                    ) {
                        Text("Laborbearbeitung beenden")
                    }
                }
                else -> {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = standardPadding),
                        onClick = {
                            if (coveringLetter != null) {
                                viewModel.giveCoveringLetterToLab(coveringLetter)
                                navController.navigate(Screen.CoveringLetters.graphRoute)
                            }
                        }
                    ) {
                        Text("Begleitschein im Labor abgeben")
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Button(
                onClick = {
                    coveringLetter?.seriesId?.let {
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
                Text("Speichern")
            }
        }
    }
}
