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
import com.christophprenissl.hygienecompanion.presentation.view.component.AddressCard
import com.christophprenissl.hygienecompanion.presentation.view.component.AddressDialog
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
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
                        SwipeToDelete(
                            onDelete = { viewModel.deleteAddress(address) }
                        ) {
                            AddressCard(
                                address = address,
                                onClick = {
                                    viewModel.chooseAddress(address)
                                    navController.navigate(Screen.SampleLocationsView.route)
                                }
                            )
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

        when(viewModel.savedAddressState.value) {
            is Response.Loading -> Text(LOADING)
            is Response.Error -> Text(ERROR)
            is Response.Success -> Text(SUCCESS)
        }
    }
}
