package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString

@Composable
fun CoveringLetterCard(
    coveringLetter: CoveringLetter,
    onClick: () -> Unit,
    accessIndicator: Boolean = true
) {
    BasicCard(
        onClick = onClick,
        accessIndicator = accessIndicator
    ) {
        Column {
            coveringLetter.description?.let { Text(it) }
            coveringLetter.date?.let { Text(it.dayMonthYearString()) }
            coveringLetter.samplingState?.let { Text(it.translation) }
        }
    }
}
