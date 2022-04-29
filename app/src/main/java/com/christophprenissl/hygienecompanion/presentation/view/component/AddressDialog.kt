package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun AddressDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    var zip by remember { mutableStateOf(TextFieldValue("")) }
    var street by remember { mutableStateOf(TextFieldValue("")) }
    var contactName by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var fax by remember { mutableStateOf(TextFieldValue("")) }
    var eMail by remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest =  onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxSize(0.8f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(modifier = Modifier.align(Alignment.TopCenter)) {
                    item {
                        Text(ADDRESS)
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        Text(ADDRESS_NAME)
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                            }
                        )
                        Text(CITY_NAME)
                        OutlinedTextField(
                            value = cityName,
                            onValueChange = {
                                cityName = it
                            }
                        )
                        Text(ZIP)
                        OutlinedTextField(
                            value = zip,
                            onValueChange = {
                                zip = it
                            }
                        )
                        Text(STREET)
                        OutlinedTextField(
                            value = street,
                            onValueChange = {
                                street = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }

                    item {
                        Text(CONTACT_DATA)
                        Text(CONTACT_NAME)
                        OutlinedTextField(
                            value = contactName,
                            onValueChange = {
                                contactName = it
                            }
                        )
                        Text(PHONE)
                        OutlinedTextField(
                            value = phone,
                            onValueChange = {
                                phone = it
                            }
                        )
                        Text(FAX)
                        OutlinedTextField(
                            value = fax,
                            onValueChange = {
                                fax = it
                            }
                        )
                        Text(EMAIL)
                        OutlinedTextField(
                            value = eMail,
                            onValueChange = {
                                eMail = it
                            }
                        )
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
                        Button(
                            modifier = Modifier.padding(standardPadding),
                            onClick = {
                                viewModel.saveAddress(
                                    name = name.text,
                                    zip = zip.text,
                                    city = cityName.text,
                                    street = street.text,
                                    phone = phone.text,
                                    fax = fax.text,
                                    eMail = eMail.text,
                                    contactName = contactName.text
                                )
                                onDismissRequest()
                            }) {
                            Text(SAVE_ADDRESS)
                        }
                    }
                }
            }
        }
    }
}
