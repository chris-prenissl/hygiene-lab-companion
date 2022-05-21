package com.christophprenissl.hygienecompanion.presentation.view.reports

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.button.BasicButton
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.SampleReport
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun ReportDetailView(
    viewModel: ReportsViewModel
) {
    val context = LocalContext.current
    val report = viewModel.chosenReport.value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(report?.description?: COVERING_LETTER)
        }
        item {
            Text( PLANNED_START_DATE + " " + report?.date?.dayMonthYearString())
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            Text(LAB_IN_DATE + " " + report?.laboratoryIn?.dayMonthYearString())
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            Text(RESULT_CREATED_DATE + " " + report?.laboratoryIn?.dayMonthYearString())
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            Text(LOT_ID + " " + report?.lotId)
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Text(BASIC_LAB_REPORT_PARAMETERS)
        }
        report?.basicLabReportParameters?.let { parameters ->
            items(parameters) { parameter ->
                Row {
                    parameter.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                    parameter.value?.let { Text(it.toString()) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }

        item {
            Text(BASIC_COVERING_PARAMETERS)
        }
        report?.basicCoveringParameters?.let { parameters ->
            items(parameters) { parameter ->
                Row {
                    parameter.name?.let { Text(it) }
                    Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                    parameter.value?.let { Text(it.toString()) }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
        }

        item {
            LazyRow{
                report?.samples?.let { samples ->
                    items(samples) { sample ->
                        SampleReport(sample = sample)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            BasicButton(onClick = {
                viewModel.requestPdfOfReportSave(context as Activity)
            }) {
                Text(SAVE_AS_PDF)
            }
            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
        }
        item {
            BasicButton(
                onClick = {
                    viewModel.openDatePickerForNewCoveringLetter(context = context)
                }
            ) {
                Text(CREATE_ADDITIONAL_COVERINGS)
            }
        }
    }
}
