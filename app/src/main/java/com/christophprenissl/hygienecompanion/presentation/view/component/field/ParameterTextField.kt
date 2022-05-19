package com.christophprenissl.hygienecompanion.presentation.view.component.field

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun ParameterTextField(
    labelText: String? = null,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { labelText?.let { Text(labelText) } },
        value = value,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange
    )
}

@Composable
fun ParameterTextField(
    labelText: String? = null,
    value: TextFieldValue,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        label = { labelText?.let { Text(labelText) } },
        value = value,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange
    )
}
