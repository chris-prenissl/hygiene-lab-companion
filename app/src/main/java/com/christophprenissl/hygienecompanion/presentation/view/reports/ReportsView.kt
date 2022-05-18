package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.CoveringLetterCard
import com.christophprenissl.hygienecompanion.util.NOT_LOADED
import com.christophprenissl.hygienecompanion.util.SORT_BY
import com.christophprenissl.hygienecompanion.util.standardPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReportsView(
    navController: NavController,
    viewModel: ReportsViewModel
){
    Column(
        modifier = Modifier.padding(standardPadding)
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
                Text("Befunde")
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }

            when(val response = viewModel.gotReportsState.value) {
                is Response.Success -> {
                    val groupedReports = response.data
                    groupedReports.forEach { (initial, reports) ->
                        initial?.let {
                            stickyHeader {
                                Text(initial)
                            }
                        }
                        items(reports) {
                            CoveringLetterCard(
                                coveringLetter = it,
                                onClick = {
                                    viewModel.chooseReport(it)
                                    navController.navigate(Screen.ReportDetail.route)
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
