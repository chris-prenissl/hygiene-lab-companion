package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ReportDetailView(
    viewModel: ReportsViewModel
) {
    Text("Detail zu ${viewModel.chosenReport.value?.description}")
}
