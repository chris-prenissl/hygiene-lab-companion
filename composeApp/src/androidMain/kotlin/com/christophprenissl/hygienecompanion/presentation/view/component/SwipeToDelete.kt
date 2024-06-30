package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDelete(
    onDelete: () -> Unit = {},
    disMissContent: @Composable (RowScope.() -> Unit),
) {
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true
            } else {
                false
            }
        },
    )
    SwipeToDismissBox(
        state = state,
        backgroundContent = {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = Icons.Rounded.Delete.name)
        },
        content = disMissContent,
    )
}
