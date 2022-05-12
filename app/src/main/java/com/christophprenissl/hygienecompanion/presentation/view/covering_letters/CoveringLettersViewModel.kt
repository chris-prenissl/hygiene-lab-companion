package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import com.christophprenissl.hygienecompanion.presentation.util.monthYearString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoveringLettersViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private var _gotCoveringLettersState = mutableStateOf<Response<Map<String?, List<CoveringLetter>>>>(Response.Success(mapOf()))
    val gotCoveringLettersState: State<Response<Map<String?, List<CoveringLetter>>>> = _gotCoveringLettersState

    private var _chosenCoveringLetter = mutableStateOf<CoveringLetter?>(null)
    val chosenCoveringLetter: State<CoveringLetter?> = _chosenCoveringLetter

    private var _savedCoveringLetterState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedCoveringLetterState: State<Response<Void?>> = _savedCoveringLetterState

    init {
        getCoveringLettersNotFinishedByDate()
    }

    private fun getCoveringLettersNotFinishedByDate() {
        viewModelScope.launch {
            useCases.getCoveringLettersNotEnded().collect { response ->
                when (response) {
                    is Response.Success -> {
                        val coveringLetters = response.data
                        val coveringLettersSorted = coveringLetters.sortedBy {
                            it.date
                        }
                        val groupedCoveringLetters = coveringLettersSorted.groupBy {
                            it.date?.monthYearString()
                        }
                        _gotCoveringLettersState.value = Response.Success(groupedCoveringLetters)
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

    private fun saveCoveringLetter(
        coveringLetter: CoveringLetter
    ) {
        viewModelScope.launch {
            useCases.saveCoveringLetter(coveringLetter).collect {
                _savedCoveringLetterState.value = it
            }
        }
    }

    fun sampleProgress(
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.SamplingInProgress
        saveCoveringLetter(coveringLetter)
    }

    fun labProgress (
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.LabInProgress
        saveCoveringLetter(coveringLetter)
    }

    fun giveCoveringLetterToLab(
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.InLaboratory
        saveCoveringLetter(coveringLetter)
    }

    fun rejectCoveringLetter(
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.SamplesNotAccepted
        saveCoveringLetter(coveringLetter)
    }

    fun finishCoveringLetterInLab(
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.LaboratoryResult
        saveCoveringLetter(coveringLetter)
    }
}
