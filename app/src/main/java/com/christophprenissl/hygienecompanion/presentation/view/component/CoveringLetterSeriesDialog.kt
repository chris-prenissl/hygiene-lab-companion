package com.christophprenissl.hygienecompanion.presentation.view.component

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
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalMaterialApi::class)
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

    var coveringParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val coveringParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }
    var coveringSampleParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val coveringSampleParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }
    var labSampleParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val labSampleParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }
    var labReportParametersKeys = remember { mutableStateListOf<ParameterBasis>() }
    val labReportParameters = remember { mutableStateMapOf<ParameterBasis, Boolean>() }

    var client by remember { mutableStateOf<Address?>(null) }
    var sampleAddress by remember { mutableStateOf<Address?>(null) }
    var samplingCompany by remember { mutableStateOf<Address?>(null) }

    val samplingLocations = remember { mutableStateListOf<SampleLocation>() }

    val types = SamplingSeriesType.values()
    var checkedType by remember { mutableStateOf(types[0]) }

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
                    item {
                        Text(COVERING_LETTER_SERIES)
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        Text(DESCRIPTION)
                        OutlinedTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            }
                        )
                        Text("Ergebnis zum Auftraggeber?")
                        Checkbox(
                            checked = resultToClient,
                            onCheckedChange = {
                                resultToClient = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
                        Text(BASIC_PARAMETERS)
                        Text("Ergebnis zur Probeentnahme-Addresse?")
                        Checkbox(
                            checked = resultToTestingProperty,
                            onCheckedChange = {
                                resultToTestingProperty = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        Text("Kostenstelle")
                        OutlinedTextField(
                            value = costLocation,
                            onValueChange = {
                                costLocation = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        Text("Labor-ID")
                        OutlinedTextField(
                            value = laboratoryId,
                            onValueChange = {
                                laboratoryId = it
                            }
                        )
                    }

                    item {
                        Text("Wahl der Untersuchungsgrundlagen")
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))

                        DropdownMenu(
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
                                            basis.coveringParameters?.forEach {
                                                coveringParameters[it] = false
                                            }
                                            coveringParametersKeys = coveringParameters.keys.toMutableStateList()

                                            basis.coveringSampleParameters?.forEach {
                                                coveringSampleParameters[it] = false
                                            }
                                            coveringSampleParametersKeys = coveringSampleParameters.keys.toMutableStateList()

                                            basis.labSampleParameters?.forEach {
                                                labSampleParameters[it] = false
                                            }
                                            labSampleParametersKeys = labSampleParameters.keys.toMutableStateList()

                                            basis.labReportParameters?.forEach {
                                                labReportParameters[it] = false
                                            }
                                            labReportParametersKeys = labReportParameters.keys.toMutableStateList()
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

                    item {
                        Text("Grundlegende Parameter")
                    }

                    items(coveringParameters.values.count()) { idx ->
                        val key = coveringParametersKeys[idx]
                        Row {
                            key.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            coveringParameters[key]?.let {
                                Checkbox(
                                    checked = it,
                                    onCheckedChange = { checked ->
                                        coveringParameters[key] = checked
                                    })
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }

                    item {
                        Text("Proben Parameter")
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

                    item {
                        Text("Proben Labor-Parameter")
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

                    item {
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }

                    item {
                        Text("Proben Befund-Parameter")
                    }

                    items(labReportParameters.values.count()) { idx ->
                        val key = labReportParametersKeys[idx]
                        Row {
                            key.name?.let { Text(it) }
                            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                            labReportParameters[key]?.let {
                                Checkbox(
                                    checked = it,
                                    onCheckedChange = { checked ->
                                        labReportParameters[key] = checked
                                    })
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }

                    item {
                        Text("Client Addresse")
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
                        Text("Probeabnahme-Firma Addresse")
                        samplingCompany?.let {
                            SwipeToDelete(onDelete = { samplingCompany = null }) {
                                AddressCard(
                                    address = it,
                                    onClick = {}
                                )
                            }
                        }
                        Button(
                            onClick = {
                                viewModel.openSamplingCompanyAddressChoice()
                            }
                        ) {
                            Text("Probeabnahme-Firma Adresse festlegen")
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
                        Text("Probeentnahme Addresse")
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
                        Button(
                            onClick = {
                                viewModel.openSampleAddressChoice()
                            }
                        ) {
                            Text("Probeentname Adresse festlegen")
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
                        Text("Wahl der Probeentnahmestellen")
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

                            Button(onClick = {
                                viewModel.openSamplingLocationsChoice()
                            }) {
                                Text("Probeentnahmestelle hinzufügen")
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
                        Text("Anfangs-Datum")
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
                            Text("Anfangs-Datum wählen")
                        }
                    }

                    item {
                        Text("Frequenz")
                        Row {
                            types.forEach { type ->
                                Column(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .selectable(
                                            selected = type == checkedType,
                                            onClick = { checkedType = type },
                                            role = Role.RadioButton
                                        )
                                        .padding(vertical = standardPadding)
                                ) {
                                    RadioButton(
                                        selected = type == checkedType,
                                        onClick = {
                                            checkedType = type
                                        }
                                    )
                                    Text(type.name)
                                }
                            }
                        }
                    }

                    if (checkedType != SamplingSeriesType.NonPeriodic) {
                        item {
                            Text("End-Datum")
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
                                Text("End-Datum wählen")
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
                                    laboratoryId = laboratoryId.text
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