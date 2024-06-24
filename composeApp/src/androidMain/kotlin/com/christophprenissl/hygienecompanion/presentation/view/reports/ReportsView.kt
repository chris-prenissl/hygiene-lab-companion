package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.card.CoveringLetterCard
import com.christophprenissl.hygienecompanion.util.NOT_LOADED
import com.christophprenissl.hygienecompanion.util.REPORTS
import com.christophprenissl.hygienecompanion.util.SORT_BY
import com.christophprenissl.hygienecompanion.util.standardPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReportsView(
    navController: NavController,
    viewModel: ReportsViewModel
){
    Column(
        modifier = Modifier.padding(standardPadding.dp)
    ) {
        if (!viewModel.reportsIsEmpty()) {
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                viewModel.groupByNextValue()
                viewModel.setNextGroupByValue()
            }) {
                Text(SORT_BY + " " + viewModel.nextGroupByState.value.name)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                TitleText(REPORTS)
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }

            when(val response = viewModel.gotReportsState.value) {
                is Response.Success -> {
                    val groupedReports = response.data
                    groupedReports.forEach { (initial, reports) ->
                        stickyHeader {
                            Text(initial)
                        }
                        items(reports) {
                            CoveringLetterCard(
                                coveringLetter = it,
                                onClick = {
                                    viewModel.chooseReport(it)
                                    navController.navigate(Screen.ReportDetail)
                                }
                            )
                        }
                    }
                }
                else -> {
                    item {
                        Text(NOT_LOADED)
                    }
                }
            }
        }
    }
}
