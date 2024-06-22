package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleText(
    title: String
) {
    Text(
        text = title,
        fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = MaterialTheme.typography.headlineLarge.fontSize
    )
}
