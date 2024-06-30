package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun SampleLocationDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit,
) {
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var extraInfo by remember { mutableStateOf(TextFieldValue("")) }
    var nextHeater by remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxSize(0.8f),
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Column(modifier = Modifier.align(Alignment.TopCenter)) {
                    Text(SAMPLE_LOCATION)
                    Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                    ParameterTextField(
                        labelText = DESCRIPTION,
                        value = description,
                        onValueChange = {
                            description = it
                        },
                    )
                    ParameterTextField(
                        labelText = EXTRA_INFO,
                        value = extraInfo,
                        onValueChange = {
                            extraInfo = it
                        },
                    )
                    ParameterTextField(
                        labelText = NEXT_HEATER,
                        value = nextHeater,
                        onValueChange = {
                            nextHeater = it
                        },
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.padding(standardPadding.dp),
                        onClick = onDismissRequest,
                    ) {
                        Text(
                            CANCEL,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Button(
                        modifier = Modifier.padding(standardPadding.dp),
                        onClick = {
                            viewModel.saveSampleLocation(
                                description = description.text,
                                extraInfo = extraInfo.text,
                                nextHeater = nextHeater.text,
                            )
                            onDismissRequest()
                        },
                    ) {
                        Text(SAVE_LOCATION)
                    }
                }
            }
        }
    }
}
