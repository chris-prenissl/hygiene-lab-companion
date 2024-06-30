package com.christophprenissl.hygienecompanion.presentation.view.component.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CancelButton(
    onCancel: () -> Unit,
    content:
    @Composable()
    (RowScope.() -> Unit),
) {
    BasicButton(
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColorFor(backgroundColor = Color.Red),
            containerColor = Color.Red,
        ),
        onClick = onCancel,
        content = content,
    )
}
