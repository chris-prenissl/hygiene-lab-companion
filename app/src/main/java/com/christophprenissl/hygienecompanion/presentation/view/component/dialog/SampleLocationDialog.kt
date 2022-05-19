package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun SampleLocationDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit
) {
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var extraInfo by remember { mutableStateOf(TextFieldValue("")) }
    var nextHeater by remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest =  onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxSize(0.8f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.align(Alignment.TopCenter)) {
                    Text(SAMPLE_LOCATION)
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    Text(DESCRIPTION)
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            description = it
                        }
                    )
                    Text(EXTRA_INFO)
                    OutlinedTextField(
                        value = extraInfo,
                        onValueChange = {
                            extraInfo = it
                        }
                    )
                    Text(NEXT_HEATER)
                    OutlinedTextField(
                        value = nextHeater,
                        onValueChange = {
                            nextHeater = it
                        }
                    )
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
                    Button(
                        modifier = Modifier.padding(standardPadding),
                        onClick = {
                            viewModel.saveSampleLocation(
                                description = description.text,
                                extraInfo = extraInfo.text,
                                nextHeater = nextHeater.text)
                            onDismissRequest()
                        }) {
                        Text(SAVE_ADDRESS)
                    }
                }
            }
        }
    }
}
