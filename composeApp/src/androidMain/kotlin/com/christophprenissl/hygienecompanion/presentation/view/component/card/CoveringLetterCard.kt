package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
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
            Text(coveringLetter.description)
            coveringLetter.date?.let { Text(it.dayMonthYearString()) }
            coveringLetter.samplingState?.let { Text(it.translation) }
        }
    }
}
