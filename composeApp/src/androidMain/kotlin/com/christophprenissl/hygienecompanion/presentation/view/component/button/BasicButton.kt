package com.christophprenissl.hygienecompanion.presentation.view.component.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun BasicButton(
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit,
    enabled: Boolean = true,
    content:
    @Composable()
    (RowScope.() -> Unit),
) {
    Button(
        colors = colors,
        modifier = Modifier.padding(standardPadding.dp),
        onClick = onClick,
        enabled = enabled,
        content = content,
    )
}
