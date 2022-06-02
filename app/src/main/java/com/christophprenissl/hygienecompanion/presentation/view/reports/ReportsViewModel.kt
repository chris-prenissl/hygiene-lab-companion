package com.christophprenissl.hygienecompanion.presentation.view.reports

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.use_case.HygieneCompanionUseCases
import com.christophprenissl.hygienecompanion.presentation.util.GroupBy
import com.christophprenissl.hygienecompanion.presentation.util.monthYearString
import com.christophprenissl.hygienecompanion.presentation.view.util.openDatePickerDialog
import com.christophprenissl.hygienecompanion.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private var _chosenReport = mutableStateOf<CoveringLetter?>(null)
    val chosenReport: State<CoveringLetter?> = _chosenReport

    private var _loadedCoveringLetterSeries = mutableStateOf<CoveringLetterSeries?>(null)
    val loadedCoveringLetterSeries: State<CoveringLetterSeries?> = _loadedCoveringLetterSeries

    private var _chosenDate = mutableStateOf(Date())
    val chosenDate: State<Date> = _chosenDate

    private var _createdAdditionalCoveringLettersState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val createdAdditionalCoveringLettersState: State<Response<Void?>> = _createdAdditionalCoveringLettersState

    private var _gotReportsState = mutableStateOf<Response<Map<String?, List<CoveringLetter>>>>(
        Response.Success(mapOf()))
    val gotReportsState: State<Response<Map<String?, List<CoveringLetter>>>> = _gotReportsState

    private var _groupByState = mutableStateOf(GroupBy.Month)
    private var groupBy: GroupBy
        set(value) {
            when (val reportsResponse = _gotReportsState.value) {
                is Response.Success -> {
                    _groupByState.value = value
                    if (reportsResponse.data.values.isEmpty()) {
                        return
                    }
                    val reportsGrouped = when(value) {
                        GroupBy.Series -> {
                            val reports = reportsResponse.data.values.reduce { acc, list -> acc + list }
                            reports.groupBy {
                                it.seriesId
                            }.mapKeys {
                                it.value[0].description
                            }
                        }
                        GroupBy.Month -> {
                            val reports = reportsResponse.data.values.reduce { acc, list -> acc + list }
                            reports.groupBy { it.resultCreated?.monthYearString() }
                        }
                    }
                    _gotReportsState.value = Response.Success(reportsGrouped)
                }
                else -> Unit
            }
        }
    get() = _groupByState.value

    private var _nextGroupByState = mutableStateOf(GroupBy.Month)
    val nextGroupByState: State<GroupBy> = _nextGroupByState

    init {
        getReports()
        setNextGroupByValue()
    }

    private fun getReports() {
        viewModelScope.launch {
            useCases.getReports().collect { response ->
                when (response) {
                    is Response.Success -> {
                        val reports = response.data
                        val reportsSorted = reports.sortedBy {
                            it.resultCreated
                        }
                        val groupedReports = when(_groupByState.value) {
                            GroupBy.Series -> {
                                reportsSorted.groupBy {
                                    it.seriesId
                                }.mapKeys {
                                    it.value[0].description
                                }
                            }
                            GroupBy.Month -> {
                                reportsSorted.groupBy {
                                    it.resultCreated?.monthYearString()
                                }
                            }
                        }

                        _gotReportsState.value = Response.Success(groupedReports)
                    }
                    is Response.Error -> _gotReportsState.value = Response.Error(response.message)
                    is Response.Loading -> _gotReportsState.value = Response.Loading
                }
            }
        }
    }

    private fun getCoveringLetterSeries(id: String) {
        viewModelScope.launch {
            useCases.getCoveringLetterSeriesById(id = id).collect {
                when (it) {
                    is Response.Success -> {
                        _loadedCoveringLetterSeries.value = it.data
                    }
                    else -> Unit
                }
            }
        }
    }

    fun setNextGroupByValue() {
        val currentIndex = _groupByState.value.ordinal
        val values = GroupBy.values()
        if (currentIndex >= values.size - 1) {
            _nextGroupByState.value = values[0]
        } else {
            _nextGroupByState.value = values[currentIndex+1]
        }
    }

    fun groupByNextValue() {
        groupBy = nextGroupByState.value
    }

    fun chooseReport(report: CoveringLetter) {
        report.seriesId?.let { getCoveringLetterSeries(it) }
        _chosenReport.value = report
    }

    fun reportsIsEmpty(): Boolean {
        return when(val state = _gotReportsState.value) {
            is Response.Success -> {
                state.data.isEmpty()
            }
            else -> true
        }
    }

    private fun createAdditionalCoveringLetters(dates: List<Date>) {
        viewModelScope.launch {
            useCases.createAdditionalCoveringLetters(
                coveringLetter = chosenReport.value!!,
                dates = dates
            ).collect {
                _createdAdditionalCoveringLettersState.value = it
            }
        }
    }

    fun openDatePickerForNewCoveringLetter(
        context: Context
    ) {
        _chosenDate.value = Date()
        openDatePickerDialog(
            context = context,
            date = _chosenDate,
            onSet = {
                createAdditionalCoveringLetters(listOf(it))
            }
        )
    }

    fun requestExcelOfReportSave(
        context: Context
    ) {
        if (!writePermissionApproved(context = context)) {
            requestWritePermission(context = context)
            return
        }
        //val path = createPdfFromReport(chosenReport.value!!, context = context)
        //Toast.makeText(context, "$path erstellt", Toast.LENGTH_SHORT).show()
    }
}
