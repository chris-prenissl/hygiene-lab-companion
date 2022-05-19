package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
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

    BasicDialog(onDismissRequest = onDismissRequest) {
        stickyHeader {
            TitleText(CREATE_ADDRESS)
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
            ParameterTextField(
                labelText = ADDRESS_NAME,
                value = name,
                onValueChange = {
                    name = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = CITY_NAME,
                value = cityName,
                onValueChange = {
                    cityName = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = ZIP,
                value = zip,
                onValueChange = {
                    zip = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = STREET,
                value = street,
                onValueChange = {
                    street = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = CONTACT_NAME,
                value = contactName,
                onValueChange = {
                    contactName = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = PHONE,
                value = phone,
                onValueChange = {
                    phone = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = FAX,
                value = fax,
                onValueChange = {
                    fax = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            ParameterTextField(
                labelText = EMAIL,
                value = eMail,
                onValueChange = {
                    eMail = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
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
