package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.card.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasisCard
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLetterSeriesDetailView(
    viewModel: CoveringLetterBasisViewModel
) {
    LazyColumn {
        stickyHeader {
            TitleText(COVERING_LETTER_SERIES)
        }
        viewModel.chosenCoveringLetterSeries.value?.let { cls ->
            item {
                ParameterText(
                    title = DESCRIPTION,
                    value =  cls.description
                )
            }
            item {
                ParameterText(
                    title = CREATED,
                    value =  cls.created?.dayMonthYearString()
                )
            }
            item {
                ParameterText(
                    title = TO_CLIENT,
                    value =  cls.resultToClient.toString()
                )
            }
            item {
                ParameterText(
                    title = TO_COVERING_PROPERTY,
                    value =  cls.resultToTestingProperty?.toString()
                )
            }
            item {
                ParameterText(
                    title = COST_LOCATION,
                    value =  cls.costLocation
                )
            }
            item {
                ParameterText(
                    title = LAB_ID,
                    value =  cls.laboratoryId
                )
            }
            item {
                Text(CLIENT_ADDRESS)
                cls.client?.let { AddressCard(it, onClick = {}) }
            }
            item {
                Text(COVERING_ADDRESS)
                cls.sampleAddress?.let { AddressCard(it, onClick = {}) }
            }
            item {
                Text(COVERING_COMPANY_ADDRESS)
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
                Text(PLANNED_END_DATE)
                cls.endedDate?.let { Text(it.toString()) }
            }
        }
    }
}
