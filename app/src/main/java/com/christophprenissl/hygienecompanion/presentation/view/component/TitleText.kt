package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleText(
    title: String
) {
    Text(
        text = title,
        fontStyle = MaterialTheme.typography.h1.fontStyle,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onBackground,
        fontSize = MaterialTheme.typography.h1.fontSize
    )
}
