package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.standardPadding
import com.christophprenissl.hygienecompanion.domain.model.Response

@Composable
fun SampleLocationsView(
    viewModel: CoveringLetterBasisViewModel
) {
    Column {
        val address = viewModel.chosenAddress
        Text("${address.value?.name}")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        when(val items = viewModel.gotSampleLocationsState.value) {
            is Response.Success -> {
                LazyColumn {
                    items(items.data) { item ->
                        Column {
                            item.description?.let { Text(it) }
                            item.address?.name?.let { Text(it) }
                        }
                    }
                }
            }
            is Response.Error -> {}
            is Response.Loading -> {}
        }
    }

}
