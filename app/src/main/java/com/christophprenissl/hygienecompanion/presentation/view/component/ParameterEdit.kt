package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.christophprenissl.hygienecompanion.domain.model.entity.Parameter
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun ParameterEdit(
    parameter: Parameter,
    value: String,
    onNumbEdit: (String) -> Unit,
    onTempEdit: (String) -> Unit,
    onBoolEdit: (Boolean) -> Unit,
    onNoteEdit: (String) -> Unit
) {
    when(parameter.parameterType) {
        ParameterType.Number -> {
            ParameterTextField(
                labelText = parameter.name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = value,
                onValueChange = onNumbEdit
            )
        }
        ParameterType.Temperature -> {
            ParameterTextField(
                labelText = parameter.name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = value,
                onValueChange = onTempEdit
            )
        }
        ParameterType.Bool -> {
            parameter.name?.let { Text(it) }
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            Checkbox(
                checked = value.toBoolean(),
                onCheckedChange = onBoolEdit
            )
        }
        ParameterType.Note -> {
            ParameterTextField(
                labelText = parameter.name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = value,
                onValueChange = onNoteEdit
            )
        }
        else -> Unit
    }
}
