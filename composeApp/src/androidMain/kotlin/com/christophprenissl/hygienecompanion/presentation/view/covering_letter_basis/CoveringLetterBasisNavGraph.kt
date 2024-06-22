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
    navigation(startDestination = Screen.CoveringLetterBasis.route, route = Screen.CoveringLetterBasis.graphRoute) {
        composable(Screen.CoveringLetterBasis.route) {
            CoveringLetterBasisView(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.SampleLocations.route) {
            SampleLocationsView(viewModel = viewModel)
        }
        composable(Screen.BasisDetail.route) {
            BasisDetailView(viewModel = viewModel)
        }
        composable(Screen.CoveringLetterSeriesDetail.route) {
            CoveringLetterSeriesDetailView(viewModel = viewModel)
        }
    }
}
