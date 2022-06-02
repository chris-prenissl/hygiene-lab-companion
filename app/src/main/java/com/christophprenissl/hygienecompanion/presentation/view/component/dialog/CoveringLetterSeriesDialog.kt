package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.entity.*
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicCheckBoxField
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicDivider
import com.christophprenissl.hygienecompanion.presentation.view.component.card.SampleLocationCard
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.button.BasicButton
import com.christophprenissl.hygienecompanion.presentation.view.component.button.CancelButton
import com.christophprenissl.hygienecompanion.presentation.view.component.button.OkButton
import com.christophprenissl.hygienecompanion.presentation.view.component.card.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasisCard
import com.christophprenissl.hygienecompanion.presentation.view.component.dropdown.BasicDropdownItem
import com.christophprenissl.hygienecompanion.presentation.view.component.dropdown.BasicDropdownMenu
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CoveringLetterSeriesDialog(
    viewModel: CoveringLetterBasisViewModel,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    var description by remember { mutableStateOf(TextFieldValue("")) }
    var resultToClient by remember { mutableStateOf(false) }
    var resultToTestingProperty by remember { mutableStateOf(false) }
    var costLocation by remember { mutableStateOf(TextFieldValue("")) }
    var laboratoryId by remember { mutableStateOf(TextFieldValue("")) }

    val basesChoices = remember { mutableStateListOf<Basis>() }
    val bases = remember { mutableStateListOf<Basis>() }

    var basicCoveringParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val basicCoveringParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }
    var coveringSampleParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val coveringSampleParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }
    var labSampleParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val labSampleParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }
    var basicLabReportParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val basicLabReportParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }

    var client by remember { mutableStateOf<Address?>(null) }
    var sampleAddress by remember { mutableStateOf<Address?>(null) }
    var samplingCompany by remember { mutableStateOf<Address?>(null) }

    val samplingLocations = remember { mutableStateListOf<SampleLocation>() }

    val types = SamplingSeriesType.values()
    var samplingSeriesType by remember { mutableStateOf(types[0]) }

    LaunchedEffect(Unit) {
        viewModel.resetDropdownExpands()
        val basesResponse = viewModel.gotBasesState.value
        if (basesResponse is Response.Success) {
            basesChoices.addAll(basesResponse.data)
        }
        samplingLocations.clear()
    }

    BasicDialog(onDismissRequest =  onDismissRequest) {
        stickyHeader {
            TitleText(COVERING_LETTER_SERIES)
        }

        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item{
            ParameterTextField(
                labelText = DESCRIPTION,
                value = description,
                onValueChange = {
                    description = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            BasicCheckBoxField(
                title = RESULT_TO_CLIENT,
                value = resultToClient,
                onCheckedChange = {
                    resultToClient = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
        }
        item {
            Row {
                BasicCheckBoxField(
                    title = RESULT_TO_COVERING_ADDRESS,
                    value = resultToTestingProperty,
                    onCheckedChange = {
                        resultToTestingProperty = it
                    }
                )
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item{
            ParameterTextField(
                labelText = COST_LOCATION,
                value = costLocation,
                onValueChange = {
                    costLocation = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item{
            ParameterTextField(
                labelText = LAB_ID,
                value = laboratoryId,
                onValueChange = {
                    laboratoryId = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
            BasicDivider()
            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
        }
        item {
            Text(CHOICE_OF_BASIS)
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
            BasicDropdownMenu(
                expanded = viewModel.openBasisDropDown.value && basesChoices.isNotEmpty(),
                onDismissRequest = {
                    viewModel.closeBasesChoice()
                }) {
                basesChoices.forEach { basis ->
                    basis.norm?.let {
                        BasicDropdownItem(
                            onClick = {
                                bases.add(basis)
                                basesChoices.remove(basis)
                                basis.basicCoveringParameters?.forEach {
                                    basicCoveringParameters[it] = false
                                }
                                basicCoveringParametersKeys = basicCoveringParameters.keys.toMutableStateList()

                                basis.coveringSampleParameters?.forEach {
                                    coveringSampleParameters[it] = false
                                }
                                coveringSampleParametersKeys = coveringSampleParameters.keys.toMutableStateList()

                                basis.labSampleParameters?.forEach {
                                    labSampleParameters[it] = false
                                }
                                labSampleParametersKeys = labSampleParameters.keys.toMutableStateList()

                                basis.basicLabReportParameters?.forEach {
                                    basicLabReportParameters[it] = false
                                }
                                basicLabReportParametersKeys = basicLabReportParameters.keys.toMutableStateList()
                            }
                        ) {
                            Text(text = it)
                        }
                    }
                }
            }
            BasicButton(onClick = {
                viewModel.openBasesChoice()
            }) {
                Text(ADD_BASIS)
            }
        }
        items(bases, key = { basis -> basis.norm!! }) {
            SwipeToDelete(onDelete = {
                viewModel.closeBasesChoice()
                basesChoices.add(it)
                bases.remove(it)
                it.basicCoveringParameters?.forEach { basis ->
                    basicCoveringParametersKeys.remove(basis)
                    basicCoveringParameters.remove(basis)
                }
                it.basicLabReportParameters?.forEach { basis ->
                    basicLabReportParametersKeys.remove(basis)
                    basicLabReportParameters.remove(basis)
                }
                it.coveringSampleParameters?.forEach { basis ->
                    coveringSampleParametersKeys.remove(basis)
                    coveringSampleParameters.remove(basis)
                }
                it.labSampleParameters?.forEach { basis ->
                    labSampleParametersKeys.remove(basis)
                    labSampleParameters.remove(basis)

                }
            }) {
                BasisCard(
                    basis = it,
                    onClick = {}
                )
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
            BasicDivider()
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        if (basicCoveringParameters.isNotEmpty()) {
            item {
                Text(BASIC_COVERING_PARAMETERS)
            }
            items(basicCoveringParameters.values.count()) { idx ->
                basicCoveringParametersKeys[idx].let { basis ->
                    basicCoveringParameters[basis]?.let { value ->
                        BasicCheckBoxField(
                            title = basis.name?: EMPTY,
                            value = value,
                            onCheckedChange = {
                                basicCoveringParameters[basis] = it
                            }
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        if (coveringSampleParameters.isNotEmpty()) {
            item {
                Text(COVERING_SAMPLE_PARAMETERS)
            }
        }
        items(coveringSampleParameters.values.count()) { idx ->
            coveringSampleParametersKeys[idx].let { basis ->
                coveringSampleParameters[basis]?.let { value ->
                    BasicCheckBoxField(
                        title = basis.name?: EMPTY, 
                        value = value, 
                        onCheckedChange = {
                            coveringSampleParameters[basis] = it
                        }
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        if (labSampleParameters.isNotEmpty()) {
            item {
                Text(LAB_SAMPLE_PARAMETERS)
            }
            items(labSampleParameters.values.count()) { idx ->
                labSampleParametersKeys[idx].let{ basis ->
                    labSampleParameters[basis]?.let { value ->
                        BasicCheckBoxField(
                            title = basis.name ?: EMPTY, 
                            value = value, 
                            onCheckedChange = {
                                labSampleParameters[basis] = it
                            }
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        if (basicLabReportParameters.isNotEmpty()) {
            item {
                Text(BASIC_LAB_REPORT_PARAMETERS)
            }
            items(basicLabReportParameters.values.count()) { idx ->
                basicLabReportParametersKeys[idx].let { basis ->
                    basicLabReportParameters[basis]?.let { value ->
                        BasicCheckBoxField(
                            title = basis.name ?: EMPTY,
                            value = value,
                            onCheckedChange = {
                                basicLabReportParameters[basis] = it
                            } 
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Text(CLIENT_ADDRESS)
            client?.let {
                SwipeToDelete(onDelete = { client = null }) {
                    AddressCard(
                        address = it,
                        onClick = {}
                    )
                }
            }
            BasicButton(
                onClick = {
                    viewModel.openClientAddressChoice()
                }
            ) {
                Text(SET_CLIENT_ADDRESS)
            }
        }
        item {
            BasicDropdownMenu(
                expanded = viewModel.openClientAddressDropDown.value,
                onDismissRequest = {
                    viewModel.closeClientAddressChoice()
                }) {
                val addresses = viewModel.gotAddressState.value
                if (addresses is Response.Success) {
                    addresses.data.forEach { address ->
                        BasicDropdownItem(onClick = {
                            client = address
                            viewModel.closeClientAddressChoice()
                        }) {
                            address.name?.let { Text(it) }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Text(COVERING_COMPANY_ADDRESS)
            samplingCompany?.let {
                SwipeToDelete(onDelete = { samplingCompany = null }) {
                    AddressCard(
                        address = it,
                        onClick = {}
                    )
                }
            }
        }

        item {
            BasicButton(
                onClick = {
                    viewModel.openSamplingCompanyAddressChoice()
                }
            ) {
                Text(SET_COVERING_COMPANY_ADDRESS)
            }
            BasicDropdownMenu(
                expanded = viewModel.openSamplingCompanyAddressDropDown.value,
                onDismissRequest = {
                    viewModel.closeSamplingCompanyAddressChoice()
                }) {
                val addresses = viewModel.gotAddressState.value
                if (addresses is Response.Success) {
                    addresses.data.forEach { address ->
                        BasicDropdownItem(onClick = {
                            samplingCompany = address
                            viewModel.closeSamplingCompanyAddressChoice()
                        }) {
                            address.name?.let { Text(it) }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Text(COVERING_ADDRESS)
            sampleAddress?.let {
                SwipeToDelete(onDelete = {
                    sampleAddress = null
                    viewModel.unChooseAddressForSampleLocations()
                }) {
                    AddressCard(
                        address = it,
                        onClick = {}
                    )
                }
            }
        }
        item {
            BasicButton(
                onClick = {
                    viewModel.openSampleAddressChoice()
                }
            ) {
                Text(SET_COVERING_ADDRESS)
            }
            BasicDropdownMenu(
                expanded = viewModel.openSampleAddressDropDown.value,
                onDismissRequest = {
                    viewModel.closeSampleAddressChoice()
                }) {
                val addresses = viewModel.gotAddressState.value
                if (addresses is Response.Success) {
                    addresses.data.forEach { address ->
                        BasicDropdownItem(onClick = {
                            sampleAddress = address
                            viewModel.chooseAddressForSampleLocations(address)
                            viewModel.closeSampleAddressChoice()
                        }) {
                            address.name?.let { Text(it) }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        if (costLocation.text.isNotEmpty()
            && sampleAddress != null
            && client != null
            && samplingCompany != null
            && description.text.isNotEmpty()
            && bases.isNotEmpty()
        ) {
            item {
                BasicDivider()
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
            item {
                Text(CHOICES_FOR_SAMPLE_LOCATIONS)
                Spacer(modifier = Modifier.padding(vertical = standardPadding))

                if (viewModel.chosenAddress.value != null) {
                    BasicDropdownMenu(
                        expanded = viewModel.openSamplingLocationsDropDown.value,
                        onDismissRequest = {
                            viewModel.closeSamplingLocationsChoice()
                        }) {
                        when (val locations = viewModel.gotSampleLocationsState.value) {
                            is Response.Success -> {
                                locations.data.forEach { location ->
                                    BasicDropdownItem(
                                        onClick = {
                                            if (!samplingLocations.contains(location)) {
                                                samplingLocations.add(location)
                                            }
                                            viewModel.closeSamplingLocationsChoice()
                                        }
                                    ) {
                                        location.description?.let { Text(it) }
                                    }
                                }
                            }
                            else -> Unit
                        }
                    }
                }
            }

            items(samplingLocations) {
                SwipeToDelete(onDelete = {
                    samplingLocations.remove(it)
                }) {
                    SampleLocationCard(sampleLocation = it)
                }
            }

            item {
                BasicButton(
                    onClick = {
                        viewModel.openSamplingLocationsChoice()
                    },
                    enabled = sampleAddress != null
                ) {
                    Text(ADD_SAMPLE_LOCATION)
                }
            }
            
            item {
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
                BasicDivider()
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
            item {
                Text(PLANNED_START_DATE)
                Spacer(modifier = Modifier.padding(vertical = standardPadding))
            }
            item {
                Text(viewModel.plannedStartDate.value.dayMonthYearString())
            }

            item {
                BasicButton(
                    onClick = {
                        viewModel.openStartDatePickerDialog(
                            context = context
                        )
                    }
                ) {
                    Text(CHOOSE_START_DATE)
                }
            }

            item {
                Text(FREQUENCY)
                Row {
                    types.forEach { type ->
                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .selectable(
                                    selected = type == samplingSeriesType,
                                    onClick = { samplingSeriesType = type },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = standardPadding)
                        ) {
                            RadioButton(
                                selected = type == samplingSeriesType,
                                onClick = {
                                    samplingSeriesType = type
                                }
                            )
                            Text(type.name)
                        }
                    }
                }
            }

            if (samplingSeriesType != SamplingSeriesType.NonPeriodic) {
                item {
                    Text(PLANNED_END_DATE)
                }
                item {
                    Text(viewModel.plannedEndDate.value.dayMonthYearString())
                }
                item {
                    BasicButton(
                        onClick = {
                            viewModel.openEndDatePickerDialog(
                                context = context
                            )
                        }
                    ) {
                        Text(CHOOSE_PLANNED_END)
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
            BasicDivider()
            Spacer(modifier = Modifier.padding(vertical = standardPadding))

            OkButton(
                onOk = {
                    viewModel.saveCoveringLetterSeries(
                        description = description.text,
                        resultToClient = resultToClient,
                        resultToTestingProperty = resultToTestingProperty,
                        costLocation = costLocation.text,
                        laboratoryId = laboratoryId.text,
                        bases = bases,
                        client = client,
                        sampleAddress = sampleAddress,
                        samplingCompany = samplingCompany,
                        sampleLocations = samplingLocations,
                        coveringParameters = basicCoveringParameters,
                        coveringSampleParameters = coveringSampleParameters,
                        labSampleParameters = labSampleParameters,
                        labReportParameters = basicLabReportParameters,
                        samplingSeriesType = samplingSeriesType,
                        plannedStart = viewModel.plannedStartDate.value,
                        plannedEnd = viewModel.plannedEndDate.value
                    )
                    onDismissRequest()
                },
                enabled = costLocation.text.isNotEmpty()
                        && sampleAddress != null
                        && client != null
                        && samplingCompany != null
                        && description.text.isNotEmpty()
                        && bases.isNotEmpty()
            ) {
                Text(ACCEPT)
            }
            CancelButton(
                onCancel = onDismissRequest
            ) {
                Text(CANCEL)
            }
        }
    }
}
