package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun BasicSurface(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(standardPadding)
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium,
        content = content
    )
}
