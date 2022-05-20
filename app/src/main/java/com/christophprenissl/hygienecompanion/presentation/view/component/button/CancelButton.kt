package com.christophprenissl.hygienecompanion.presentation.view.component.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CancelButton(
    onCancel: () -> Unit,
    content: @Composable() (RowScope.() -> Unit)
) {
    BasicButton(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        onClick = onCancel,
        content = content
    )
}
