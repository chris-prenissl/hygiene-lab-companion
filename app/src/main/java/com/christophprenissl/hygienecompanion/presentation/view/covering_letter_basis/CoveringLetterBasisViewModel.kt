package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoveringLetterBasisViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private val _savedSampleLocationState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedSampleLocationState: State<Response<Void?>> = _savedSampleLocationState

    fun saveSampleLocation(description: String) {
        val sampleLocation = SampleLocation(
            description = description
        )

        viewModelScope.launch {
            useCases.saveSampleLocation(sampleLocation).collect { response ->
                _savedSampleLocationState.value = response
            }
        }
    }
}
