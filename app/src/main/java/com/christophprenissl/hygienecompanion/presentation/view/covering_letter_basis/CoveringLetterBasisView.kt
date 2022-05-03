package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.*
import com.christophprenissl.hygienecompanion.util.*

import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class)
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
                modifier = Modifier.padding(standardPadding),
                text = COVERING_LETTER_BASIS_DATA
            )
        }

        when (val addressesResponse = viewModel.gotAddressState.value) {
            is Response.Success -> {
                val addresses = addressesResponse.data
                items(addresses) { address ->
                    SwipeToDelete(
                        onDelete = { viewModel.deleteAddress(address) }
                    ) {
                        AddressCard(
                            address = address,
                            onClick = {
                                viewModel.chooseAddressForSampleLocations(address)
                                navController.navigate(Screen.SampleLocations.route)
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
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        item {
            Text(
                modifier = Modifier.padding(vertical = standardPadding),
                text = COVERING_BASIS
            )
        }

        when (val basesResponse = viewModel.gotBasesState.value) {
            is Response.Success -> {
                val bases = basesResponse.data
                items(bases) { basis ->
                    SwipeToDelete(
                        onDelete = { viewModel.deleteBasis(basis) }
                    ) {
                        BasisCard(
                            basis = basis,
                            onClick = {
                                viewModel.chooseBasis(basis)
                                navController.navigate(Screen.BasisDetail.route)
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
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        
        item {
            Text(COVERING_LETTER_SERIES)
        }

        when (val coveringLetterSeriesResponse = viewModel.gotCoveringLetterSeriesNotEndedState.value) {
            is Response.Success -> {
                items(coveringLetterSeriesResponse.data) { coveringLetterSeries ->
                    SwipeToDelete(
                        onDelete = {  }
                    ) {
                        CoveringLetterSeriesCard(
                            coveringLetterSeries = coveringLetterSeries,
                            onClick = {
                                viewModel.chooseCoveringLetterSeries(coveringLetterSeries)
                                navController.navigate(Screen.CoveringLetterSeriesDetail.route)
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
                Text("Begleitscheinserie hinzufügen")
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
    }
}
