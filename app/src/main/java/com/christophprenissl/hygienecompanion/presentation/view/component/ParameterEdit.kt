package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
            Row(
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                        ),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(standardPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                parameter.name?.let { Text(it) }
                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                Checkbox(
                    checked = value.toBoolean(),
                    onCheckedChange = onBoolEdit
                )
            }
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
