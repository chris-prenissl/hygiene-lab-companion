package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoveringLettersViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private var _gotCoveringLetterSeriesState = mutableStateOf<Response<List<CoveringLetterSeries>>>(Response.Success(listOf()))
    val gotCoveringLetterSeriesState: State<Response<List<CoveringLetterSeries>>> = _gotCoveringLetterSeriesState

    init {
        getCoveringLettersNotFinishedByTime()
    }

    private fun getCoveringLettersNotFinishedByTime() {
        viewModelScope.launch {
            useCases.getCoveringLetterSeriesNotEnded().collect { response ->
                _gotCoveringLetterSeriesState.value = response
            }
        }
    }
}
