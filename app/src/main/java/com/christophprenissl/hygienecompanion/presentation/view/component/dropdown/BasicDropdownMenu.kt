package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.dropDownMenuWidth

@Composable
fun BasicDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(dropDownMenuWidth),
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            content = content
        )
    }
}
