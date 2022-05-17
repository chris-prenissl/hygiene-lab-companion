package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.ExperimentalFoundationApi
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasisDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit
) {
    var norm by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val basicCoveringParameters = remember { mutableStateListOf<ParameterBasis>() }
    val coveringSampleParameters = remember { mutableStateListOf<ParameterBasis>() }
    val labSampleParameters = remember { mutableStateListOf<ParameterBasis>() }
    val basicLabReportParameters = remember { mutableStateListOf<ParameterBasis>() }

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
                    LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        stickyHeader {
                            Text(BASIS)
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        }
                        item {
                            Text(NORM)
                            OutlinedTextField(
                                value = norm,
                                onValueChange = {
                                    norm = it
                                }
                            )
                        }
                        item {
                            Text(DESCRIPTION)
                            OutlinedTextField(
                                value = description,
                                onValueChange = {
                                    description = it
                                }
                            )
                            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
                        }

                        item {
                            Text(BASIC_COVERING_PARAMETERS)
                        }
                        items(basicCoveringParameters) { item ->
                            ParameterCreationItem(
                                item = item,
                                onDelete = {
                                    basicCoveringParameters.remove(item)
                                }
                            )
                        }
                        item {
                            Button(onClick = {
                                basicCoveringParameters.add(ParameterBasis(parameterType = ParameterType.Number))
                            }) {
                                Text(ADD_PARAMETER)
                            }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        }

                        item {
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
                        }

                        item {
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
                        }

                        item {
                            Text(BASIC_LAB_REPORT_PARAMETERS)
                        }
                        items(basicLabReportParameters) { item ->
                            ParameterCreationItem(
                                item = item,
                                onDelete = { basicLabReportParameters.remove(item) }
                            )
                        }
                        item {
                            Button(onClick = {
                                basicLabReportParameters.add(ParameterBasis(parameterType = ParameterType.Number))
                            }) {
                                Text(ADD_PARAMETER)
                            }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))


                        }

                        item {
                            Button(
                                modifier = Modifier.padding(standardPadding),
                                onClick = {
                                    viewModel.saveBasis(
                                        norm = norm.text,
                                        description = description.text,
                                        basicCoveringParameters = basicCoveringParameters,
                                        coveringSampleParameters = coveringSampleParameters,
                                        labSampleParameters = labSampleParameters,
                                        basicLabReportParameters = basicLabReportParameters
                                    )
                                    viewModel.closeBasisDialog()
                                }
                            ) {
                                Text(ACCEPT)
                            }
                        }
                        item {
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
