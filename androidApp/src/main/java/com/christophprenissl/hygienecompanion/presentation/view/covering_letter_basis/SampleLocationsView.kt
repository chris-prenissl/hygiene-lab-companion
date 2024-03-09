package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.standardPadding
import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.presentation.view.component.card.SampleLocationCard
import com.christophprenissl.hygienecompanion.presentation.view.component.dialog.SampleLocationDialog
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.util.NEW_SAMPLE_LOCATION

@Composable
fun SampleLocationsView(
    viewModel: CoveringLetterBasisViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(standardPadding.dp)
    ) {
        val address = viewModel.chosenAddress
        Text("${address.value?.name}")
        Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))

        if (viewModel.openSampleLocationState.value) {
            SampleLocationDialog(
                viewModel = viewModel,
                onDismissRequest = { viewModel.closeSampleLocationDialog() }
            )
        }

        when(val response = viewModel.gotSampleLocationsState.value) {
            is Response.Success -> {
                LazyColumn {
                    items(response.data, key = {location -> location.id}) { item ->
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
