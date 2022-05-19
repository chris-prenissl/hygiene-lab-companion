package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis

@Composable
fun BasisCard(
    basis: Basis,
    onClick: () -> Unit
) {
    BasicCard(
        onClick = onClick,
        accessIndicator = true
    ) {
        Column {
            basis.description?.let { Text(it) }
            basis.norm?.let { Text(it) }
        }
    }
}
