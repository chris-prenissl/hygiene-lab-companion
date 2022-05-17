package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import com.christophprenissl.hygienecompanion.presentation.util.GroupBy
import com.christophprenissl.hygienecompanion.presentation.util.monthYearString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private var _chosenReport = mutableStateOf<CoveringLetter?>(null)
    val chosenReport: State<CoveringLetter?> = _chosenReport

    private var _gotReportsState = mutableStateOf<Response<Map<String?, List<CoveringLetter>>>>(
        Response.Success(mapOf()))
    val gotReportsState: State<Response<Map<String?, List<CoveringLetter>>>> = _gotReportsState

    private var _groupByState = mutableStateOf(GroupBy.Month)
    private var groupBy: GroupBy
        set(value) {
            when (val reportsResponse = _gotReportsState.value) {
                is Response.Success -> {
                    _groupByState.value = value
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
        _chosenReport.value = report
    }
}
