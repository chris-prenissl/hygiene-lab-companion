package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.christophprenissl.hygienecompanion.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface
import com.christophprenissl.hygienecompanion.presentation.view.component.button.BasicButton
import com.christophprenissl.hygienecompanion.presentation.view.component.card.LabWorkerCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.SamplerCard
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.SampleReport
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.util.translation
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun ReportDetailView(
    viewModel: ReportsViewModel
) {
    val context = LocalContext.current
    val report = viewModel.chosenReport.value

    BasicSurface {
        LazyColumn(
            contentPadding = PaddingValues(vertical = standardPadding),
            modifier = Modifier
                .fillMaxSize()
                .padding(doubleStandardPadding)
        ) {
            item {
                ParameterText(
                    title = COVERING_LETTER,
                    value =  report?.description
                )
            }
            item {
                ParameterText(
                    title = PLANNED_START_DATE,
                    value =  report?.date?.dayMonthYearString()
                )
            }
            item {
                ParameterText(
                    title = LAB_IN_DATE,
                    value =  report?.laboratoryIn?.dayMonthYearString()
                )
            }
            item {
                ParameterText(
                    title = RESULT_CREATED_DATE,
                    value =  report?.resultCreated?.dayMonthYearString()
                )
            }
            item {
                ParameterText(
                    title = LOT_ID,
                    value = report?.lotId
                )
            }
            item {
                Text(SAMPLER)
            }
            report?.sampler?.let {
                item {
                    SamplerCard(user = it)
                }
            }
            item {
                Text(LAB_WORKER)
            }
            report?.labWorker?.let {
                item {
                    LabWorkerCard(user = it)
                }
            }
            item {
                Text(BASIC_LAB_REPORT_PARAMETERS)
            }
            report?.basicLabReportParameters?.let { parameters ->
                items(parameters) { parameter ->
                    ParameterText(
                        title = parameter.name ?: EMPTY,
                        value = parameter.value
                    )
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
                    ParameterText(
                        title = parameter.name ?: EMPTY,
                        value = parameter.value
                    )
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
                report?.let {
                    if (viewModel.loadedCoveringLetterSeries.value != null) {
                        BasicButton(
                            onClick = {
                                createXSSFFromReport(
                                    context = context,
                                    coveringLetterSeries = viewModel.loadedCoveringLetterSeries.value!!,
                                    report = report
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = contentColorFor(
                                    backgroundColor = MaterialTheme.colors.secondary
                                )
                            )
                        ) {
                            Text(SAVE_AS_EXCEL)
                        }
                        Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
                    }
                }
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
}
