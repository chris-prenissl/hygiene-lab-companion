package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun AddressDialog(viewModel: CoveringLetterBasisViewModel) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    var zip by remember { mutableStateOf(TextFieldValue("")) }
    var street by remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest =  { viewModel.closeAddressDialog() }) {
        Surface(
            modifier = Modifier.fillMaxSize(0.8f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.align(Alignment.TopCenter)) {
                    Text(ADDRESS)
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    Text(NAME)
                    TextField(
                        value = name,
                        onValueChange = {
                            name = it
                        }
                    )
                    Text(CITY_NAME)
                    TextField(
                        value = cityName,
                        onValueChange = {
                            cityName = it
                        }
                    )
                    Text(ZIP)
                    TextField(
                        value = zip,
                        onValueChange = {
                            zip = it
                        }
                    )
                    Text(STREET)
                    TextField(
                        value = street,
                        onValueChange = {
                            street = it
                        }
                    )
                    Row {
                        Button(onClick = { viewModel.closeAddressDialog() }) {
                            Text(CANCEL)
                        }
                        Button(
                            onClick = {
                                viewModel.saveAddress(
                                    name = name.text,
                                    zip = zip.text,
                                    city = cityName.text,
                                    street = street.text)
                            }) {
                            Text(SAVE_ADDRESS)
                        }
                    }
                }
            }
        }
    }
}
