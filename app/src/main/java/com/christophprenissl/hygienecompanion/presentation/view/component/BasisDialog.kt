package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun BasisDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit
) {
    var norm by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val coveringParameters = remember { mutableStateListOf<ParameterBasis>() }
    val coveringSampleParameters = remember { mutableStateListOf<ParameterBasis>() }
    val labSampleParameters = remember { mutableStateListOf<ParameterBasis>() }
    val labReportParameters = remember { mutableStateListOf<ParameterBasis>() }

    Dialog(onDismissRequest =  onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxSize(0.9f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier
                    .align(Alignment.TopCenter)
                ) {
                    Text(BASIC_PARAMETERS)
                    LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Text(COVERING_BASIS)
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                            Text(NORM)
                            OutlinedTextField(
                                value = norm,
                                onValueChange = {
                                    norm = it
                                }
                            )
                            Text(DESCRIPTION)
                            OutlinedTextField(
                                value = description,
                                onValueChange = {
                                    description = it
                                }
                            )
                            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
                            Text(BASIC_PARAMETERS)
                        }

                        items(coveringParameters) { item ->
                            ParameterCreationItem(
                                item = item,
                                onDelete = {
                                    coveringParameters.remove(item)
                                }
                            )
                        }

                        item {
                            Button(onClick = {
                                coveringParameters.add(ParameterBasis(parameterType = ParameterType.Number))
                            }) {
                                Text(ADD_PARAMETER)
                            }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                            Text(COVERING_SAMPLE_PARAMETERS)
                        }

                        items(coveringSampleParameters) { item ->
                            ParameterCreationItem(
                                item = item,
                                onDelete = { coveringSampleParameters.remove(item) }
                            )
                        }

                        item {
                            Button(onClick = {
                                coveringSampleParameters.add(ParameterBasis(parameterType = ParameterType.Number))
                            }) {
                                Text(ADD_PARAMETER)
                            }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                            Text(LAB_SAMPLE_PARAMETERS)
                        }

                        items(labSampleParameters) { item ->
                            ParameterCreationItem(
                                item = item,
                                onDelete = { labSampleParameters.remove(item) }
                            )
                        }

                        item {
                            Button(onClick = {
                                labSampleParameters.add(ParameterBasis(parameterType = ParameterType.Number))
                            }) {
                                Text(ADD_PARAMETER)
                            }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                            Text(LAB_REPORT_PARAMETERS)
                        }

                        items(labReportParameters) { item ->
                            ParameterCreationItem(
                                item = item,
                                onDelete = { labReportParameters.remove(item) }
                            )
                        }

                        item {
                            Button(onClick = {
                                labReportParameters.add(ParameterBasis(parameterType = ParameterType.Number))
                            }) {
                                Text(ADD_PARAMETER)
                            }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))

                            Button(
                                modifier = Modifier.padding(standardPadding),
                                onClick = {
                                    viewModel.saveBasis(
                                        norm = norm.text,
                                        description = description.text,
                                        coveringParameters = coveringParameters,
                                        coveringSampleParameters = coveringSampleParameters,
                                        labSampleParameters = labSampleParameters,
                                        labReportParameters = labReportParameters
                                    )
                                    viewModel.closeBasisDialog()
                                }
                            ) {
                                Text(ACCEPT)
                            }
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                                modifier = Modifier.padding(standardPadding),
                                onClick = onDismissRequest
                            ) {
                                Text(
                                    CANCEL,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
