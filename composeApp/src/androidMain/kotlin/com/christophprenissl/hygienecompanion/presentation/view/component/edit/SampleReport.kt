package com.christophprenissl.hygienecompanion.presentation.view.component.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.entity.Sample
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasicCard
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun SampleReport(
    sample: Sample,
) {
    BasicCard(
        elevation = standardElevation.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(standardPadding.dp),
        ) {
            Row {
                Text(SAMPLE_LOCATION)
                Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
                sample.sampleLocation.description.let { Text(it) }
            }
            Row {
                Text(SAMPLE_ID)
                Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
                Text(sample.id)
            }
            Row {
                Text(SAMPLING_DATE)
                Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
                sample.created?.let { Text(it.dayMonthYearString()) }
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }
            Text(EXTRA_INFO_SAMPLING_PERSON)
            Text(sample.extraInfoSampling)
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))

            Text(EXTRA_INFO_LAB_PERSON)
            Text(sample.extraInfoLaboratory)
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))

            Text(WARNING)
            Text(sample.warningMessage)
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))

            Text(LAB_SAMPLE_PARAMETERS)
            sample.labSampleParameters.forEach { parameter ->
                ParameterText(parameter.name, parameter.value)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))

            Text(COVERING_SAMPLE_PARAMETERS)
            sample.coveringSampleParameters.forEach { parameter ->
                ParameterText(parameter.name, parameter.value)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
    }
}
