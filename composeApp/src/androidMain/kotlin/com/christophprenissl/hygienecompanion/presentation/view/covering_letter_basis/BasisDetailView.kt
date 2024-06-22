package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun BasisDetailView(
    viewModel: CoveringLetterBasisViewModel
) {
    LazyColumn {
        item {
            Row {
                Text(NORM)
                Spacer(Modifier.padding(horizontal = standardPadding.dp))
                viewModel.chosenBasis.value?.norm?.let { Text(it) }
            }
            Row {
                Text(DESCRIPTION)
                Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
                viewModel.chosenBasis.value?.description?.let { Text(it) }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        viewModel.chosenBasis.value?.basicCoveringParameters?.let { parameters ->
            item {
                Text(BASIC_COVERING_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    Text(item.name)
                    Spacer(modifier = Modifier.padding(standardPadding.dp))
                    item.parameterType.translation.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }
        }

        viewModel.chosenBasis.value?.coveringSampleParameters?.let { parameters ->
            item {
                Text(COVERING_SAMPLE_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    Text(item.name)
                    Spacer(modifier = Modifier.padding(standardPadding.dp))
                    item.parameterType.translation.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }
        }

        viewModel.chosenBasis.value?.labSampleParameters?.let { parameters ->
            item {
                Text(LAB_SAMPLE_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    Text(item.name)
                    Spacer(modifier = Modifier.padding(standardPadding.dp))
                    item.parameterType.translation.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }
        }

        viewModel.chosenBasis.value?.basicLabReportParameters?.let { parameters ->
            item {
                Text(BASIC_LAB_REPORT_PARAMETERS)
            }
            items(parameters) { item ->
                Row {
                    Text(item.name)
                    Spacer(modifier = Modifier.padding(standardPadding.dp))
                    item.parameterType.translation.let { Text(it) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }
        }
    }
}
