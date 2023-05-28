package com.christophprenissl.hygienecompanion.presentation.view.component.edit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun ParameterBooleanEdit(
    parameterName: String,
    value: String,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(standardPadding.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(parameterName)
        Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
        Checkbox(
            checked = value.toBoolean(),
            onCheckedChange = onCheckedChange
        )
    }
}
