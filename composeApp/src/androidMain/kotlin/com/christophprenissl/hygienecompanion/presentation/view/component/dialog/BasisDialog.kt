package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.button.BasicButton
import com.christophprenissl.hygienecompanion.presentation.view.component.button.CancelButton
import com.christophprenissl.hygienecompanion.presentation.view.component.button.OkButton
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterCreationItem
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasisDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit,
) {
    var pIdx by remember { mutableStateOf(0) }
    var norm by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val basicCoveringParameters = remember { mutableStateListOf<ParameterBasis>() }
    val coveringSampleParameters = remember { mutableStateListOf<ParameterBasis>() }
    val labSampleParameters = remember { mutableStateListOf<ParameterBasis>() }
    val basicLabReportParameters = remember { mutableStateListOf<ParameterBasis>() }

    BasicDialog(onDismissRequest = onDismissRequest) {
        stickyHeader {
            TitleText(BASIS)
        }
        item {
            ParameterTextField(
                labelText = NORM,
                value = norm,
                onValueChange = {
                    norm = it
                },
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        item {
            ParameterTextField(
                labelText = DESCRIPTION,
                value = description,
                onValueChange = {
                    description = it
                },
            )
            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding.dp))
        }

        item {
            Text(BASIC_COVERING_PARAMETERS)
        }
        items(basicCoveringParameters, key = { item -> item.creationId }) { item ->
            ParameterCreationItem(
                item = item,
                onDelete = {
                    basicCoveringParameters.remove(item)
                },
            )
        }
        item {
            BasicButton(
                onClick = {
                    basicCoveringParameters.add(
                        ParameterBasis(
                            creationId = pIdx,
                            name = "",
                            parameterType = ParameterType.Number,
                        ),
                    )
                    pIdx++
                },
            ) {
                Text(ADD_PARAMETER)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }

        item {
            Text(COVERING_SAMPLE_PARAMETERS)
        }
        items(coveringSampleParameters, key = { item -> item.creationId }) { item ->
            ParameterCreationItem(
                item = item,
                onDelete = { coveringSampleParameters.remove(item) },
            )
        }
        item {
            BasicButton(
                onClick = {
                    coveringSampleParameters.add(
                        ParameterBasis(
                            creationId = pIdx,
                            name = "",
                            parameterType = ParameterType.Number,
                        ),
                    )
                    pIdx++
                },
            ) {
                Text(ADD_PARAMETER)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }

        item {
            Text(LAB_SAMPLE_PARAMETERS)
        }
        items(labSampleParameters, key = { item -> item.creationId }) { item ->
            ParameterCreationItem(
                item = item,
                onDelete = { labSampleParameters.remove(item) },
            )
        }
        item {
            BasicButton(
                onClick = {
                    labSampleParameters.add(
                        ParameterBasis(
                            creationId = pIdx,
                            name = "",
                            parameterType = ParameterType.Number,
                        ),
                    )
                    pIdx++
                },
            ) {
                Text(ADD_PARAMETER)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }

        item {
            Text(BASIC_LAB_REPORT_PARAMETERS)
        }
        items(basicLabReportParameters, key = { item -> item.creationId }) { item ->
            ParameterCreationItem(
                item = item,
                onDelete = { basicLabReportParameters.remove(item) },
            )
        }
        item {
            BasicButton(
                onClick = {
                    basicLabReportParameters.add(
                        ParameterBasis(
                            creationId = pIdx,
                            name = "",
                            parameterType = ParameterType.Number,
                        ),
                    )
                    pIdx++
                },
            ) {
                Text(ADD_PARAMETER)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        item {
            OkButton(
                onOk = {
                    viewModel.saveBasis(
                        norm = norm.text,
                        description = description.text,
                        basicCoveringParameters = basicCoveringParameters,
                        coveringSampleParameters = coveringSampleParameters,
                        labSampleParameters = labSampleParameters,
                        basicLabReportParameters = basicLabReportParameters,
                    )
                    viewModel.closeBasisDialog()
                },
            ) {
                Text(ACCEPT)
            }
        }
        item {
            CancelButton(
                onCancel = onDismissRequest,
            ) {
                Text(
                    CANCEL,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
