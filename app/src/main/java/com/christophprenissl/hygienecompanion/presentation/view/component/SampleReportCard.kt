package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun SampleReportCard(
    sample: Sample
) {
    Column {
        Row {
            Text("Probeentnahme-Stelle")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.sampleLocation?.description?.let { Text(it) }
        }
        Row {
            Text("Probennummer")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.id?.let { Text(it) }
        }
        Row {
            Text("Datum")
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            sample.created?.let { Text(it.dayMonthYearString()) }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        Text("Zusatzinfo der probenentnehmenden Person")
        Text(sample.extraInfoSampling?: "---")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text("Zusatzinfo Laborarbeiter:in")
        Text(sample.extraInfoLaboratory?: "---")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text("Warnung")
        Text(sample.warningMessage?: "---")
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text("Labor-Parameter")
        sample.labSampleParameters?.forEach { parameter ->
            Row {
                parameter.name?.let { Text(it) }
                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                Text(parameter.value.toString())
            }
        }
        Spacer(modifier = Modifier.padding(vertical = standardPadding))

        Text("Probeentnahme-Parameter")
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
