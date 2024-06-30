package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.Basis

@Composable
fun BasisCard(
    basis: Basis,
    onClick: () -> Unit = {},
    accessAble: Boolean = true,
) {
    BasicCard(
        onClick = onClick,
        accessIndicator = accessAble,
    ) {
        Column {
            Text(basis.description)
            Text(basis.norm)
        }
    }
}
