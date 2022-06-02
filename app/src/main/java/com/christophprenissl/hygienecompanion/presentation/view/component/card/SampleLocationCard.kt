package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.SampleLocation

@Composable
fun SampleLocationCard(
    sampleLocation: SampleLocation,
) {
    BasicCard {
        Column {
            sampleLocation.description?.let { Text(it) }
            sampleLocation.extraInfo?.let { Text(it) }
            sampleLocation.nextHeater?.let { Text(it) }
            sampleLocation.address?.name?.let { Text(it) }
        }
    }
}
