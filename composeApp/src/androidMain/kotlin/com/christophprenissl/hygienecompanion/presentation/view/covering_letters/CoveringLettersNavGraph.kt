package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Route
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.coveringLettersGraph(
    navController: NavController,
    viewModel: CoveringLettersViewModel
) {

    navigation<Route.CoveringLetters>(
        startDestination = Screen.CoveringLetters
    ) {
        composable<Screen.CoveringLetters> {
            CoveringLettersView(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable<Screen.CoveringLetterDetail> {
            CoveringLetterDetailView(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
