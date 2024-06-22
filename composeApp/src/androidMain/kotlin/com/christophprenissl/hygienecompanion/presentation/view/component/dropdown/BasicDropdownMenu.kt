package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.dropDownMenuWidth

@Composable
fun BasicDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart),
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
