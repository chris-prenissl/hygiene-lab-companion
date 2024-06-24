package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.*
import com.christophprenissl.hygienecompanion.presentation.view.component.card.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.BasisCard
import com.christophprenissl.hygienecompanion.presentation.view.component.card.CoveringLetterSeriesCard
import com.christophprenissl.hygienecompanion.presentation.view.component.dialog.AddressDialog
import com.christophprenissl.hygienecompanion.presentation.view.component.dialog.BasisDialog
import com.christophprenissl.hygienecompanion.presentation.view.component.dialog.CoveringLetterSeriesDialog
import com.christophprenissl.hygienecompanion.util.*

import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@InternalComposeApi
@ExperimentalCoroutinesApi
fun CoveringLetterBasisView(
    navController: NavController,
    viewModel: CoveringLetterBasisViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(
                modifier = Modifier.padding(standardPadding.dp),
                text = COVERING_LETTER_BASIS_DATA
            )
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }

        item {
            Text("Adressen / Probeentnahme-Orte")
        }

        when (val addressesResponse = viewModel.gotAddressState.value) {
            is Response.Success -> {
                val addresses = addressesResponse.data
                items(addresses, key = {address -> address.id}) { address ->
                    SwipeToDelete(
                        onDelete = {
                            viewModel.deleteAddress(address)
                        }
                    ) {
                        AddressCard(
                            address = address,
                            onClick = {
                                viewModel.chooseAddressForSampleLocations(address)
                                navController.navigate(Screen.SampleLocations)
                            }
                        )
                    }
                }
            }
            is Response.Loading -> {
                item {
                    Text(LOADING)
                }
            }
            is Response.Error -> {
                item {
                    Text(ERROR)
                }
            }
        }

        item {
            when (viewModel.openAddressDialogState.value) {
                true -> AddressDialog(
                    viewModel = viewModel,
                    onDismissRequest = {
                        viewModel.closeAddressDialog()
                    }
                )
                false -> Unit
            }
        }

        item {
            Button (onClick = {
                viewModel.openAddressDialog()
            }) {
                Text(ADD_ADDRESS)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }

        item {
            Text(
                modifier = Modifier.padding(vertical = standardPadding.dp),
                text = COVERING_BASIS
            )
        }

        when (val basesResponse = viewModel.gotBasesState.value) {
            is Response.Success -> {
                val bases = basesResponse.data
                items(bases, key = {basis -> basis.norm}) { basis ->
                    SwipeToDelete(
                        onDelete = { viewModel.deleteBasis(basis) }
                    ) {
                        BasisCard(
                            basis = basis,
                            onClick = {
                                viewModel.chooseBasis(basis)
                                navController.navigate(Screen.BasisDetail)
                            }
                        )
                    }
                }
            }

            is Response.Loading -> {
                item {
                    Text(LOADING)
                }
            }
            is Response.Error -> {
                item {
                    Text(ERROR)
                }
            }
        }

        item {
            when (viewModel.openBasisDialogState.value) {
                true -> BasisDialog(
                    viewModel = viewModel,
                    onDismissRequest = {
                        viewModel.closeBasisDialog()
                    }
                )
                false -> Unit
            }
        }
        
        item {
            Button(onClick = {
                viewModel.openBasisDialog()
            }) {
                Text(ADD_COVERING_BASIS)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        
        item {
            Text(COVERING_LETTER_SERIES)
        }

        when (val coveringLetterSeriesResponse = viewModel.gotCoveringLetterSeriesNotEndedState.value) {
            is Response.Success -> {
                val coveringLetterSeries = coveringLetterSeriesResponse.data
                items(coveringLetterSeries, key = {cs -> cs.id}) { series ->
                    SwipeToDelete(
                        onDelete = {  }
                    ) {
                        CoveringLetterSeriesCard(
                            coveringLetterSeries = series,
                            onClick = {
                                viewModel.chooseCoveringLetterSeries(series)
                                navController.navigate(Screen.CoveringLetterSeriesDetail)
                            }
                        )
                    }
                }
            }

            is Response.Loading -> {
                item {
                    Text(LOADING)
                }
            }
            is Response.Error -> {
                item {
                    Text(ERROR)
                }
            }
        }

        item {
            when (viewModel.openCoveringLetterSeriesDialog.value) {
                true -> CoveringLetterSeriesDialog(
                    viewModel = viewModel,
                    onDismissRequest = {
                        viewModel.closeCoveringLetterSeriesDialog()
                    }
                )
                false -> Unit
            }
        }

        item {
            Button(onClick = {
                viewModel.openCoveringLetterSeriesDialog()
            }) {
                Text(ADD_COVERING_LETTER_SERIES)
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
    }
}
