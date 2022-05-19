package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.*
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.component.card.SampleLocationCard
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.presentation.view.component.card.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasisCard
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
        val basesResponse = viewModel.gotBasesState.value
        if (basesResponse is Response.Success) {
            basesChoices.addAll(basesResponse.data)
        }
    }

    Dialog(onDismissRequest =  onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxSize(0.9f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    stickyHeader {
                        Text(COVERING_LETTER_SERIES)
                    }

                    item {
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }
                    item {
                        Text(DESCRIPTION)
                        OutlinedTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            }
                        )
                    }
                    item {
                        Row {
                            Text(RESULT_TO_CLIENT)
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            Checkbox(
                                checked = resultToClient,
                                onCheckedChange = {
                                    resultToClient = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
                    }
                    item {
                        Row {
                            Text(RESULT_TO_COVERING_ADDRESS)
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            Checkbox(
                                checked = resultToTestingProperty,
                                onCheckedChange = {
                                    resultToTestingProperty = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }
                    item {
                        Row {
                            Text(COST_LOCATION)
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            OutlinedTextField(
                                value = costLocation,
                                onValueChange = {
                                    costLocation = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }
                    item {
                        Row {
                            Text(LAB_ID)
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            OutlinedTextField(
                                value = laboratoryId,
                                onValueChange = {
                                    laboratoryId = it
                                }
                            )
                        }
                    }

                    item {
                        Text(CHOICE_OF_BASIS)
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        DropdownMenu(
                            modifier = Modifier.fillMaxWidth(),
                            expanded = viewModel.openBasisDropDown.value,
                            onDismissRequest = {
                                viewModel.closeBasesChoice()
                            }) {
                            basesChoices.forEach { basis ->
                                basis.norm?.let {
                                    DropdownMenuItem(
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

                        Button(onClick = {
                            viewModel.openBasesChoice()
                        }) {
                            Text("Basis hinzufügen")
                        }
                    }
                    items(bases) {
                        SwipeToDelete(onDelete = {
                            basesChoices.add(it)
                            bases.remove(it)
                        }) {
                            BasisCard(
                                basis = it,
                                onClick = {}
                            )
                        }
                    }

                    if (basicCoveringParameters.isNotEmpty()) {
                        item {
                            Text(BASIC_COVERING_PARAMETERS)
                        }
                        items(basicCoveringParameters.values.count()) { idx ->
                            val key = basicCoveringParametersKeys[idx]
                            Row {
                                key.name?.let { Text(it) }
                                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                                basicCoveringParameters[key]?.let {
                                    Checkbox(
                                        checked = it,
                                        onCheckedChange = { checked ->
                                            basicCoveringParameters[key] = checked
                                        })
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
                        val key = coveringSampleParametersKeys[idx]
                        Row {
                            key.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            coveringSampleParameters[key]?.let {
                                Checkbox(
                                    checked = it,
                                    onCheckedChange = { checked ->
                                        coveringSampleParameters[key] = checked
                                    })
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
                            val key = labSampleParametersKeys[idx]
                            Row {
                                key.name?.let { Text(it) }
                                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                                labSampleParameters[key]?.let {
                                    Checkbox(
                                        checked = it,
                                        onCheckedChange = { checked ->
                                            labSampleParameters[key] = checked
                                        })
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
                            val key = basicLabReportParametersKeys[idx]
                            Row {
                                key.name?.let { Text(it) }
                                Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                                basicLabReportParameters[key]?.let {
                                    Checkbox(
                                        checked = it,
                                        onCheckedChange = { checked ->
                                            basicLabReportParameters[key] = checked
                                        })
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
                        Button(
                            onClick = {
                                viewModel.openClientAddressChoice()
                            }
                        ) {
                            Text("Client Adresse festlegen")
                        }
                    }
                    item {
                        DropdownMenu(
                            expanded = viewModel.openClientAddressDropDown.value,
                            onDismissRequest = {
                                viewModel.closeClientAddressChoice()
                            }) {
                            val addresses = viewModel.gotAddressState.value
                            if (addresses is Response.Success) {
                                addresses.data.forEach { address ->
                                    DropdownMenuItem(onClick = {
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
                        Button(
                            onClick = {
                                viewModel.openSamplingCompanyAddressChoice()
                            }
                        ) {
                            Text(SET_COVERING_COMPANY_ADDRESS)
                        }
                        DropdownMenu(
                            expanded = viewModel.openSamplingCompanyAddressDropDown.value,
                            onDismissRequest = {
                                viewModel.closeSamplingCompanyAddressChoice()
                            }) {
                            val addresses = viewModel.gotAddressState.value
                            if (addresses is Response.Success) {
                                addresses.data.forEach { address ->
                                    DropdownMenuItem(onClick = {
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
                        Button(
                            onClick = {
                                viewModel.openSampleAddressChoice()
                            }
                        ) {
                            Text(SET_COVERING_ADDRESS)
                        }
                        DropdownMenu(
                            expanded = viewModel.openSampleAddressDropDown.value,
                            onDismissRequest = {
                                viewModel.closeSampleAddressChoice()
                            }) {
                            val addresses = viewModel.gotAddressState.value
                            if (addresses is Response.Success) {
                                addresses.data.forEach { address ->
                                    DropdownMenuItem(onClick = {
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

                    item {
                        Text(CHOICES_FOR_SAMPLE_LOCATIONS)
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))

                        if (viewModel.chosenAddress.value != null) {
                            DropdownMenu(
                                expanded = viewModel.openSamplingLocationsDropDown.value,
                                onDismissRequest = {
                                    viewModel.closeSamplingLocationsChoice()
                                }) {
                                when (val locations = viewModel.gotSampleLocationsState.value) {
                                    is Response.Success -> {
                                        locations.data.forEach { location ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    if (!samplingLocations.contains(location)) {
                                                        samplingLocations.add(location)
                                                    }
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
                        Button(onClick = {
                            viewModel.openSamplingLocationsChoice()
                        }) {
                            Text(ADD_SAMPLE_LOCATION)
                        }
                    }

                    item {
                        Text(PLANNED_START_DATE)
                    }
                    item {
                        Text(viewModel.plannedStartDate.value.dayMonthYearString())
                    }

                    item {
                        Button(
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
                            Button(
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

                    item {
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))

                        Button(
                            modifier = Modifier.padding(standardPadding),
                            onClick = {
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
                            }
                        ) {
                            Text(ACCEPT)
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                            modifier = Modifier.padding(standardPadding),
                            onClick = onDismissRequest
                        ) {
                            Text(
                                CANCEL,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}