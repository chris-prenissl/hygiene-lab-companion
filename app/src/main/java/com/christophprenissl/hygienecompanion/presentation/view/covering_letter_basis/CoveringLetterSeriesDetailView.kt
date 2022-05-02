package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.presentation.view.component.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.BasisCard

@Composable
fun CoveringLetterSeriesDetailView(
    viewModel: CoveringLetterBasisViewModel
) {
    LazyColumn {
        item {
            Text("Covering Letter Series")
        }
        viewModel.chosenCoveringLetterSeries.value?.let { cls ->
            item {
                Text("Beschreibung")
                cls.description?.let { Text(it) }
            }
            item {
                Text("Erstellt:")
                cls.created?.let { Text(it.toString()) }
            }
            item {
                Text("An den Auftraggeber Ergebnis senden:")
                cls.resultToClient?.let { Text(it.toString()) }
            }
            item {
                Text("An die Abnahmestelle senden:")
                cls.resultToTestingProperty?.let { Text(it.toString()) }
            }
            item {
                Text("Kostenstelle:")
                cls.costLocation?.let { Text(it) }
            }
            item {
                Text("Labor-ID:")
                cls.laboratoryId?.let { Text(it) }
            }
            item {
                Text("Client")
                cls.client?.let { AddressCard(it, onClick = {}) }
            }
            item {
                Text("Probeentnahme-Addresse")
                cls.sampleAddress?.let { AddressCard(it, onClick = {}) }
            }
            item {
                Text("Probeentnahme-Firma")
                cls.samplingCompany?.let { AddressCard(it, onClick = {}) }
            }
            cls.bases?.let { basesList ->
                items(basesList) { item ->
                    BasisCard(
                        basis = item,
                        onClick = {}
                    )
                }
            }
            item {
                Text("End-Datum")
                cls.endedDate?.let { Text(it.toString()) }
            }
        }
    }
}
