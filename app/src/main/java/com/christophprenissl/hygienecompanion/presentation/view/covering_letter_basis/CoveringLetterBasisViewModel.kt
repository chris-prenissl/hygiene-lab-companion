package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.*
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

    private val _deletedSampleLocationState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val deletedSampleLocationState: State<Response<Void?>> = _deletedSampleLocationState

    private val _gotSampleLocationsState = mutableStateOf<Response<List<SampleLocation>>>(Response.Success(listOf()))
    val gotSampleLocationsState: State<Response<List<SampleLocation>>> = _gotSampleLocationsState

    private val _openAddressDialogState = mutableStateOf(false)
    val openAddressDialogState: State<Boolean> = _openAddressDialogState

    private val _openSampleLocationDialogState = mutableStateOf(false)
    val openSampleLocationState: State<Boolean> = _openSampleLocationDialogState

    private val _savedAddressState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedAddressState: State<Response<Void?>> = _savedAddressState

    private val _deleteAddressState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val deleteAddressState: State<Response<Void?>> = _deleteAddressState

    private val _gotAddressesState = mutableStateOf<Response<List<Address>>>(Response.Success(listOf()))
    val gotAddressState: State<Response<List<Address>>> = _gotAddressesState

    private val _openBasisDialogState = mutableStateOf(false)
    val openBasisDialogState: State<Boolean> = _openBasisDialogState

    private val _gotBasesState = mutableStateOf<Response<List<Basis>>>(Response.Success(listOf()))
    val gotBasesState: State<Response<List<Basis>>> = _gotBasesState

    private val _savedBasisState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedBasisState: State<Response<Void?>> = _savedBasisState

    private val _deletedBasisState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val deletedBasisState: State<Response<Void?>> = _deletedBasisState

    private var _chosenBasis = mutableStateOf<Basis?>(null)
    val chosenBasis = _chosenBasis

    private var _gotCoveringLetterSeriesNotEndedState = mutableStateOf<Response<List<CoveringLetterSeries>>>(Response.Success(listOf()))
    val gotCoveringLetterSeriesNotEndedState: State<Response<List<CoveringLetterSeries>>> = _gotCoveringLetterSeriesNotEndedState

    private var _savedCoveringLetterSeriesState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val savedCoveringLetterSeriesState: State<Response<Void?>> = _savedCoveringLetterSeriesState

    init {
        getAddresses()
        getBases()
        getCoveringLetterSeriesNotEnded()
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
            useCases.getSampleLocations(fromAddress).collect { response ->
                _gotSampleLocationsState.value = response
            }
        }
    }

    fun deleteSampleLocation(sampleLocation: SampleLocation) {
        viewModelScope.launch {
            useCases.deleteSampleLocation(sampleLocation).collect { response ->
                _deletedSampleLocationState.value = response
            }
        }
    }

    fun chooseAddress(address: Address) {
        _chosenAddress.value = address
        getSampleLocations(address)
    }

    fun saveSampleLocation(
        description: String?,
        extraInfo: String?,
        nextHeater: String?
    ) {
        val sampleLocation = SampleLocation(
            description = description,
            extraInfo = extraInfo,
            nextHeater = nextHeater,
            address = chosenAddress.value
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

    fun openSampleLocationDialog() {
        _openSampleLocationDialogState.value = true
    }

    fun closeAddressDialog() {
        _openAddressDialogState.value = false
    }

    fun closeSampleLocationDialog() {
        _openSampleLocationDialogState.value = false
    }

    private fun getBases() {
        viewModelScope.launch {
            useCases.getBases().collect { response ->
                _gotBasesState.value = response
            }
        }
    }

    fun saveBasis(
        norm: String,
        description: String,
        coveringParameters: List<ParameterBasis>,
        coveringSampleParameters: List<ParameterBasis>,
        labSampleParameters: List<ParameterBasis>,
        labReportParameters: List<ParameterBasis>
    ) {
        val basis = Basis(
            norm = norm,
            description = description,
            coveringParameters = coveringParameters,
            coveringSampleParameters = coveringSampleParameters,
            labSampleParameters = labSampleParameters,
            labReportParameters = labReportParameters
        )
        viewModelScope.launch {
            useCases.saveBasis(basis).collect { response ->
                _savedBasisState.value = response
            }
        }
    }

    fun deleteBasis(basis: Basis) {
        viewModelScope.launch {
            useCases.deleteBasis(basis).collect { response ->
                _deletedBasisState.value = response
            }
        }
    }

    fun chooseBasis(basis: Basis) {
        _chosenBasis.value = basis
    }

    fun openBasisDialog() {
        _openBasisDialogState.value = true
    }

    fun closeBasisDialog() {
        _openBasisDialogState.value = false
    }

    fun saveAddress(
        name: String,
        zip: String,
        city: String,
        contactName: String,
        street: String,
        phone: String,
        fax: String,
        eMail: String
    ) {
        val newAddress = Address(
            name = name,
            zip = zip,
            city = city,
            contactName = contactName,
            street = street,
            phone = phone,
            fax = fax,
            eMail = eMail
        )
        viewModelScope.launch {
            useCases.saveAddress(newAddress).collect { response ->
                _savedAddressState.value = response
                _openAddressDialogState.value = false
            }
        }
    }

    fun deleteAddress(address: Address) {
        viewModelScope.launch {
            useCases.deleteAddress(address).collect { response ->
                _deleteAddressState.value = response
            }
        }
    }

    fun saveCoveringLetterSeries(coveringLetterSeries: CoveringLetterSeries) {
        viewModelScope.launch {
            useCases.saveCoveringLetterSeries(coveringLetterSeries).collect { response ->
                _savedCoveringLetterSeriesState.value = response
            }
        }
    }

    fun getCoveringLetterSeriesNotEnded() {
        viewModelScope.launch {
            useCases.getCoveringLetterSeries().collect { response ->
                _gotCoveringLetterSeriesNotEndedState.value = response
            }
        }
    }
}
