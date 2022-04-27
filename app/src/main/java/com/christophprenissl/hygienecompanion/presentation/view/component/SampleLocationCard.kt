package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.util.cardPadding
import com.christophprenissl.hygienecompanion.util.halfSize

@Composable
fun SampleLocationCard(
    sampleLocation: SampleLocation,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(halfSize)
            .padding(cardPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                sampleLocation.description?.let { Text(it) }
                sampleLocation.extraInfo?.let { Text(it) }
                sampleLocation.nextHeater?.let { Text(it) }
                sampleLocation.address?.name?.let { Text(it) }
            }
        }
    }
}
