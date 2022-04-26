package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SampleLocationsView(
    viewModel: CoveringLetterBasisViewModel
) {
    val address = viewModel.chosenAddress
    Text("${address.value?.name}")
}
