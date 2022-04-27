package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoveringLetterBasisViewModel @Inject constructor(
    private val useCases: HygieneCompanionUseCases
): ViewModel() {

    private var _chosenAddress = mutableStateOf<Address?>(null)
    val chosenAddress = _chosenAddress

    private val _savedSampleLocationState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedSampleLocationState: State<Response<Void?>> = _savedSampleLocationState

    private val _gotSampleLocationsState = mutableStateOf<Response<List<SampleLocation>>>(Response.Success(listOf()))
    val gotSampleLocationsState: State<Response<List<SampleLocation>>> = _gotSampleLocationsState

    private val _openAddressDialogState = mutableStateOf<Boolean>(false)
    val openAddressDialogState: State<Boolean> = _openAddressDialogState

    private val _savedAddressState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedAddressState: State<Response<Void?>> = _savedAddressState

    private val _deleteAddressState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val deleteAddressState: State<Response<Void?>> = _deleteAddressState

    private val _gotAddressesState = mutableStateOf<Response<List<Address>>>(Response.Success(listOf()))
    val gotAddressState: State<Response<List<Address>>> = _gotAddressesState


    init {
        getAddresses()
    }


    private fun getAddresses() {
        viewModelScope.launch {
            useCases.getAddresses().collect { response ->
                _gotAddressesState.value = response
            }
        }
    }

    private fun getSampleLocations(fromAddress: Address) {
        viewModelScope.launch {
            useCases.getSampleLocations(fromAddress).collect() { response ->
                _gotSampleLocationsState.value = response
            }
        }
    }

    fun chooseAddress(address: Address) {
        _chosenAddress.value = address
        getSampleLocations(address)
    }

    fun unChooseAddress() {
        _chosenAddress.value = null
    }

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

    fun openAddressDialog() {
        _openAddressDialogState.value = true
    }

    fun saveAddress(
        name: String,
        zip: String,
        city: String,
        street: String
    ) {
        val newAddress = Address(
            name = name,
            zip = zip,
            city = city,
            street = street
        )
        viewModelScope.launch {
            useCases.saveAddress(newAddress).collect() { response ->
                _savedAddressState.value = response
                _openAddressDialogState.value = false
            }
        }
    }

    fun deleteAddress(address: Address) {
        viewModelScope.launch {
            useCases.deleteAddress(address).collect() { response ->
                _deleteAddressState.value = response
            }
        }
    }

    fun closeAddressDialog() {
       _openAddressDialogState.value = false
    }
}
