package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.christophprenissl.hygienecompanion.util.titleSize

@Composable
fun TitleText(
    title: String
) {
    Text(
        text = title,
        fontSize = titleSize,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onBackground
    )
}
