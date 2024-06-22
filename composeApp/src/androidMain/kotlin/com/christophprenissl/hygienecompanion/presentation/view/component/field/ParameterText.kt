package com.christophprenissl.hygienecompanion.presentation.view.component.field

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.EMPTY
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun ParameterText(
    title: String,
    value: String?
) {
    Row {
        Text(title)
        Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
        Text(value?: EMPTY)
    }
}
