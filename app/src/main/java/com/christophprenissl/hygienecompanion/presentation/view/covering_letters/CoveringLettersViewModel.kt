package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoveringLettersViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private var _gotCoveringLettersState = mutableStateOf<Response<List<CoveringLetter>>>(Response.Success(listOf()))
    val gotCoveringLettersState: State<Response<List<CoveringLetter>>> = _gotCoveringLettersState

    private var _chosenCoveringLetter = mutableStateOf<CoveringLetter?>(null)
    val chosenCoveringLetter: State<CoveringLetter?> = _chosenCoveringLetter

    init {
        getCoveringLettersNotFinishedByTime()
    }

    private fun getCoveringLettersNotFinishedByTime() {
        viewModelScope.launch {
            useCases.getCoveringLetterSeriesNotEnded().collect { response ->
                val coveringLetters = mutableListOf<CoveringLetter>()
                when (response) {
                    is Response.Success -> {
                        response.data.forEach { cls ->
                            cls.coveringLetters?.forEach {
                                coveringLetters.add(it)
                            }
                        }
                        coveringLetters.sortBy {
                            it.date
                        }
                        _gotCoveringLettersState.value = Response.Success(coveringLetters)
                    }
                    is Response.Error -> _gotCoveringLettersState.value = Response.Error(response.message)
                    is Response.Loading -> _gotCoveringLettersState.value = Response.Loading
                }
            }
        }
    }

    fun chooseCoveringLetter(coveringLetter: CoveringLetter) {
        _chosenCoveringLetter.value = coveringLetter
    }
}
