package com.christophprenissl.hygienecompanion.presentation.view.component.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun SampleReport(
    sample: Sample
) {
    Column(
        Modifier.padding(standardPadding)
    ) {
        Row {
            Text(SAMPLE_LOCATION)
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.sampleLocation?.description?.let { Text(it) }
        }
        Row {
            Text(SAMPLE_ID)
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.id?.let { Text(it) }
        }
        Row {
            Text(SAMPLING_DATE)
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.created?.let { Text(it.dayMonthYearString()) }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        Text(EXTRA_INFO_SAMPLING_PERSON)
        Text(sample.extraInfoSampling?: "---")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text(EXTRA_INFO_LAB_PERSON)
        Text(sample.extraInfoLaboratory?: "---")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text(WARNING)
        Text(sample.warningMessage?: "---")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text(LAB_SAMPLE_PARAMETERS)
        sample.labSampleParameters?.forEach { parameter ->
            Row {
                parameter.name?.let { Text(it) }
                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                Text(parameter.value.toString())
            }
        }
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text(COVERING_SAMPLE_PARAMETERS)
        sample.coveringSampleParameters?.forEach { parameter ->
            Row {
                parameter.name?.let { Text(it) }
                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                Text(parameter.value.toString())
            }
        }
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
    }
}
