package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown

import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface

@Composable
fun BasicDropdownItem(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DropdownMenuItem(onClick = onClick) {
        BasicSurface(content = content)
    }
}
