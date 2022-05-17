package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.standardPadding
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.presentation.view.component.SampleLocationCard
import com.christophprenissl.hygienecompanion.presentation.view.component.SampleLocationDialog
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.util.NEW_SAMPLE_LOCATION

@Composable
fun SampleLocationsView(
    viewModel: CoveringLetterBasisViewModel
) {
    Column {
        val address = viewModel.chosenAddress
        Text("${address.value?.name}")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        if (viewModel.openSampleLocationState.value) {
            SampleLocationDialog(
                viewModel = viewModel,
                onDismissRequest = { viewModel.closeSampleLocationDialog() }
            )
        }

        when(val response = viewModel.gotSampleLocationsState.value) {
            is Response.Success -> {
                LazyColumn {
                    items(response.data) { item ->
                        SwipeToDelete(onDelete = {
                            viewModel.deleteSampleLocation(item)
                        }) {
                            SampleLocationCard(sampleLocation = item)
                        }
                    }
                }
            }
            is Response.Error -> {
                Text(response.message)
            }
            is Response.Loading -> Unit
        }
        Button(onClick = { viewModel.openSampleLocationDialog() }) {
            Text(NEW_SAMPLE_LOCATION)
        }
    }
}
