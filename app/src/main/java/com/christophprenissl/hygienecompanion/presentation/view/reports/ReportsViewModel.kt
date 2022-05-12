package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
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

    init {
        getReports()
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
                        val groupedReports = reportsSorted.groupBy {
                            it.resultCreated?.monthYearString()
                        }
                        _gotReportsState.value = Response.Success(groupedReports)
                    }
                    is Response.Error -> _gotReportsState.value = Response.Error(response.message)
                    is Response.Loading -> _gotReportsState.value = Response.Loading
                }
            }
        }
    }

    fun chooseReport(report: CoveringLetter) {
        _chosenReport.value = report
    }
}
