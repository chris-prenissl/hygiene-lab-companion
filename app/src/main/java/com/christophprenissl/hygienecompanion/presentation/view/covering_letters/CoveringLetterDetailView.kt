package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.presentation.view.component.SampleCard

@Composable
fun CoveringLetterDetailView(
    viewModel: CoveringLettersViewModel
) {
    val coveringLetter = viewModel.chosenCoveringLetter.value
    val title = coveringLetter?.description ?: "Probe-Entnahme"
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(title)
        }

        item {
            LazyRow{
                coveringLetter?.samples?.let { samples ->
                    items(samples) {
                        SampleCard(sample = it)
                    }
                }
            }
        }
    }
}
