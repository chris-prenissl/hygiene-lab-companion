package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun BasisDetailView(
    viewModel: CoveringLetterBasisViewModel
) {
    LazyColumn {
        item {
            Row {
                Text(NORM)
                Spacer(Modifier.padding(horizontal = standardPadding))
                viewModel.chosenBasis.value?.norm?.let { Text(it) }
            }
            Row {
                Text(DESCRIPTION)
                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                viewModel.chosenBasis.value?.description?.let { Text(it) }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        viewModel.chosenBasis.value?.coveringParameters?.let { parameters ->
            item {
                Text(BASIC_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    item.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(standardPadding))
                    item.parameterType?.name?.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }

        viewModel.chosenBasis.value?.coveringSampleParameters?.let { parameters ->
            item {
                Text(COVERING_SAMPLE_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    item.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(standardPadding))
                    item.parameterType?.name?.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }

        viewModel.chosenBasis.value?.labSampleParameters?.let { parameters ->
            item {
                Text(LAB_SAMPLE_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    item.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(standardPadding))
                    item.parameterType?.name?.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }

        viewModel.chosenBasis.value?.labReportParameters?.let { parameters ->
            item {
                Text(LAB_REPORT_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    item.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(standardPadding))
                    item.parameterType?.name?.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }
    }
}