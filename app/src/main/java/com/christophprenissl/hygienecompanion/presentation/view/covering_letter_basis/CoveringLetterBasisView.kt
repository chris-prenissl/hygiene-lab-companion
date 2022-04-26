package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.presentation.view.component.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.AddressDialog
import com.christophprenissl.hygienecompanion.util.*

import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@InternalComposeApi
@ExperimentalCoroutinesApi
fun CoveringLetterBasisView(
    viewModel: CoveringLetterBasisViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(standardPadding),
            text = COVERING_LETTER_BASIS_DATA
        )

        when (viewModel.openAddressDialogState.value) {
            true -> AddressDialog(
                viewModel = viewModel
            )
            false -> Unit
        }

        when (val addressesResponse = viewModel.gotAddressState.value) {
            is Response.Success -> {
                LazyColumn {
                    val addresses = addressesResponse.data
                    items(addresses) { address ->
                        AddressCard(address = address) {
                            viewModel.deleteAddress(address)
                        }
                    }
                }
            }
            is Response.Loading -> {
                Text(LOADING)
            }
            is Response.Error -> {
                Text(ERROR)
            }
        }

        Button (onClick = {
            viewModel.openAddressDialog()
        }) {
            Text(ADD_ADDRESS)
        }

        Button(onClick = {
            viewModel.saveSampleLocation("Description")
        }) {
            Text("Add Sample Location")
        }
        when(viewModel.savedSampleLocationState.value) {
            is Response.Loading -> Text(LOADING)
            is Response.Error -> Text(ERROR)
            is Response.Success -> Text(SUCCESS)
        }
    }
}