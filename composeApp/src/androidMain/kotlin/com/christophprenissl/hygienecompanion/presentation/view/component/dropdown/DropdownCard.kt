package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasicCard

@Composable
fun DropdownCard(
    onClick: () -> Unit,
    menuOpen: Boolean,
    content: @Composable (RowScope.() -> Unit)
) {
    BasicCard(
        onClick = onClick,
        indicatorIcon = if (menuOpen) Icons.Rounded.ArrowDropUp else Icons.Rounded.ArrowDropDown,
        content = content
    )
}
