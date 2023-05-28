package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun BasicCheckBoxField(
    title: String,
    value: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(title)
        Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
        Checkbox(
            checked = value,
            onCheckedChange = onCheckedChange
        )
    }
}
