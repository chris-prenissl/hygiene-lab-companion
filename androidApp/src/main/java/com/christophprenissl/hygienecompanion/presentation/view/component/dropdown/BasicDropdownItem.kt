package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable

@Composable
fun BasicDropdownItem(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DropdownMenuItem(text = content, onClick = onClick)
}
