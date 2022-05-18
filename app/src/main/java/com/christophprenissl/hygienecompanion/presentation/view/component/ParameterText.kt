package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun ParameterText(
    title: String,
    value: String?
) {
    Row {
        Text(title)
        Spacer(modifier = Modifier.padding(horizontal = standardPadding))
        Text(value?: "---")
    }
}