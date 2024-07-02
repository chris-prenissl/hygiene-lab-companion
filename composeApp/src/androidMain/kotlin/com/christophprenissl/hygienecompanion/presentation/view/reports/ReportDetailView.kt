package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface
import com.christophprenissl.hygienecompanion.presentation.view.component.button.BasicButton
import com.christophprenissl.hygienecompanion.presentation.view.component.card.LabWorkerCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.SamplerCard
import com.christophprenissl.hygienecompanion.presentation.view.component.edit.SampleReport
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.util.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReportDetailView(
    viewModel: ReportsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val report = viewModel.chosenReport.value

    BasicSurface {
        LazyColumn(
            contentPadding = PaddingValues(vertical = standardPadding.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(doubleStandardPadding.dp),
        ) {
            item {
                ParameterText(
                    title = COVERING_LETTER,
                    value = report?.description,
                )
            }
            item {
                ParameterText(
                    title = PLANNED_START_DATE,
                    value = report?.date?.dayMonthYearString(),
                )
            }
            item {
                ParameterText(
                    title = LAB_IN_DATE,
                    value = report?.laboratoryIn?.dayMonthYearString(),
                )
            }
            item {
                ParameterText(
                    title = RESULT_CREATED_DATE,
                    value = report?.resultCreated?.dayMonthYearString(),
                )
            }
            item {
                ParameterText(
                    title = LOT_ID,
                    value = report?.lotId,
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
                        title = parameter.name,
                        value = parameter.value,
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                }
            }

            item {
                Text(BASIC_COVERING_PARAMETERS)
            }
            report?.basicCoveringParameters?.let { parameters ->
                items(parameters) { parameter ->
                    ParameterText(
                        title = parameter.name,
                        value = parameter.value,
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                }
            }

            item {
                LazyRow {
                    report?.samples?.let { samples ->
                        items(samples) { sample ->
                            SampleReport(sample = sample)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
            }
            item {
                report?.let {
                    if (viewModel.loadedCoveringLetterSeries.value != null) {
                        BasicButton(
                            onClick = {
                                TODO()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = contentColorFor(
                                    backgroundColor = MaterialTheme.colorScheme.secondary,
                                ),
                            ),
                        ) {
                            Text(SAVE_AS_EXCEL)
                        }
                        Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding.dp))
                    }
                }
            }
            item {
                BasicButton(
                    onClick = {
                        viewModel.openDatePickerForNewCoveringLetter(context = context)
                    },
                ) {
                    Text(CREATE_ADDITIONAL_COVERINGS)
                }
            }
        }
    }
}
