package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import com.christophprenissl.hygienecompanion.presentation.util.monthYearString
import com.christophprenissl.hygienecompanion.util.DataStoreUser
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoveringLettersViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases,
    dataStoreUser: DataStoreUser
): ViewModel() {

    private var _gotCoveringLettersState = mutableStateOf<Response<Map<String?, List<CoveringLetter>>>>(Response.Success(mapOf()))
    val gotCoveringLettersState: State<Response<Map<String?, List<CoveringLetter>>>> = _gotCoveringLettersState

    private var _chosenCoveringLetter = mutableStateOf<CoveringLetter?>(null)
    val chosenCoveringLetter: State<CoveringLetter?> = _chosenCoveringLetter

    private var _savedCoveringLetterState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedCoveringLetterState: State<Response<Void?>> = _savedCoveringLetterState

    val userTypeFlow: Flow<UserType?> = dataStoreUser.getUserType()
    val userFlow: Flow<User?> = dataStoreUser.getUser()

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
        sampler: User,
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.SamplingInProgress
        coveringLetter.sampler = sampler
        saveCoveringLetter(coveringLetter)
    }

    fun labProgress (
        labWorker: User,
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.samplingState = SamplingState.LabInProgress
        coveringLetter.labWorker = labWorker
        saveCoveringLetter(coveringLetter)
    }

    fun giveCoveringLetterToLab(
        sampler: User,
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.sampler = sampler
        coveringLetter.samplingState = SamplingState.InLaboratory
        coveringLetter.laboratoryIn = Timestamp.now().toDate()
        saveCoveringLetter(coveringLetter)
    }

    fun rejectCoveringLetter(
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.labWorker = null
        coveringLetter.samplingState = SamplingState.SamplesNotAccepted
        saveCoveringLetter(coveringLetter)
    }

    fun finishCoveringLetterInLab(
        labWorker: User,
        coveringLetter: CoveringLetter
    ) {
        coveringLetter.labWorker = labWorker
        coveringLetter.samplingState = SamplingState.LaboratoryResult
        coveringLetter.resultCreated = Timestamp.now().toDate()
        saveCoveringLetter(coveringLetter)
    }
}
