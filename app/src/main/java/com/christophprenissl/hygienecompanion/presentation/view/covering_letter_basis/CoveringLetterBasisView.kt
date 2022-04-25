package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.christophprenissl.hygienecompanion.domain.model.Response

import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@InternalComposeApi
@ExperimentalCoroutinesApi
fun CoveringLetterBasisView(
    viewModel: CoveringLetterBasisViewModel = hiltViewModel()
) {
    Column {
        Text("Covering Letter Basis")
        Button(onClick = {
            viewModel.saveSampleLocation("Description")
        }) {
            Text("Add Sample Location")
        }
        when(viewModel.savedSampleLocationState.value) {
            is Response.Loading -> Text("loading")
            is Response.Error -> Text("ERROR")
            is Response.Success -> Text("success")
        }
    }
}