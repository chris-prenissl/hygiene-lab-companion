package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CoveringLetterSeriesDetailView(
    viewModel: CoveringLetterBasisViewModel
) {
    Column {
        Text("Covering Letter Series")
        viewModel.chosenCoveringLetterSeries.value?.description?.let { Text(it) }
    }
}
