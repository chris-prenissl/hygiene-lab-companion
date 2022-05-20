package com.christophprenissl.hygienecompanion.presentation.view.component.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun OkButton(
    enabled: Boolean = true,
    onOk: () -> Unit,
    content: @Composable() (RowScope.() -> Unit)
) {
    BasicButton(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
        onClick = onOk,
        enabled = enabled,
        content = content
    )
}
