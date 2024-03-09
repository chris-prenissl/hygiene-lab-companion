package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.card.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasisCard
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.util.translation
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLetterSeriesDetailView(
    viewModel: CoveringLetterBasisViewModel
) {
    BasicSurface(
        modifier = Modifier
            .padding(standardPadding.dp)
            .fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                TitleText(COVERING_LETTER_SERIE)
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
                        value =  cls.resultToClient.translation()
                    )
                }
                item {
                    ParameterText(
                        title = TO_COVERING_PROPERTY,
                        value =  cls.resultToTestingProperty.translation()
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
                    cls.client?.let { AddressCard(it, accessAble = false) }
                }
                item {
                    Text(COVERING_ADDRESS)
                    cls.sampleAddress?.let { AddressCard(it, accessAble = false) }
                }
                item {
                    Text(COVERING_COMPANY_ADDRESS)
                    cls.samplingCompany?.let { AddressCard(it, accessAble = false) }
                }
                items(cls.bases) { item ->
                    BasisCard(
                        basis = item,
                        accessAble = false
                    )
                }
                item {
                    Text(PLANNED_END_DATE)
                    cls.endedDate?.let { Text(it.toString()) }
                }
            }
        }
    }

}
