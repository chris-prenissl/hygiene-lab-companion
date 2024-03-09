package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.SampleLocation

@Composable
fun SampleLocationCard(
    sampleLocation: SampleLocation,
) {
    BasicCard {
        Column {
            Text(sampleLocation.description)
            Text(sampleLocation.extraInfo)
            Text(sampleLocation.nextHeater)
            sampleLocation.address?.name?.let { Text(it) }
        }
    }
}
