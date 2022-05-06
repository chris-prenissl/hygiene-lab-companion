package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.coveringLettersGraph(
    navController: NavController,
    viewModel: CoveringLettersViewModel
) {

    navigation(
        startDestination = Screen.CoveringLetters.route,
        route = Screen.CoveringLetters.graphRoute
    ) {
        composable(Screen.CoveringLetters.route) {
            CoveringLettersView(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.CoveringLetterDetail.route) {
            CoveringLetterDetailView(viewModel = viewModel)
        }
    }
}
