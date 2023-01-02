package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetterSeries

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
            Text(coveringLetterSeries.description)
            Text(coveringLetterSeries.samplingSeriesType.name)
        }
    }
}
