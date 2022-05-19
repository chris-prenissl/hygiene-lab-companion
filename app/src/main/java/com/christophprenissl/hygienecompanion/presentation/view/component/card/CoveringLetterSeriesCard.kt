package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries

@Composable
fun CoveringLetterSeriesCard(
    coveringLetterSeries: CoveringLetterSeries,
    onClick: () -> Unit
) {
    BasicCard(
        onClick = onClick,
        accessIndicator = true
    ) {
        Column {
            coveringLetterSeries.description?.let { Text(it) }
            coveringLetterSeries.samplingSeriesType?.let { Text(it.name) }
        }
    }
}