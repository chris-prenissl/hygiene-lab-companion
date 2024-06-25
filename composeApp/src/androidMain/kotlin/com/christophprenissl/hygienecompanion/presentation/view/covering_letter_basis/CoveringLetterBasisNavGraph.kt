package com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis

import androidx.compose.runtime.InternalComposeApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(InternalComposeApi::class, ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.coveringLetterBasisNavGraph(
    navController: NavController,
    viewModel: CoveringLetterBasisViewModel
) {
    navigation<Screen.CoveringLetterBasis>(startDestination = Screen.CoveringLetterBasis) {
        composable<Screen.CoveringLetterBasis> {
            CoveringLetterBasisView(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable<Screen.SampleLocations> {
            SampleLocationsView(viewModel = viewModel)
        }
        composable<Screen.BasisDetail> {
            BasisDetailView(viewModel = viewModel)
        }
        composable<Screen.CoveringLetterSeriesDetail> {
            CoveringLetterSeriesDetailView(viewModel = viewModel)
        }
    }
}
