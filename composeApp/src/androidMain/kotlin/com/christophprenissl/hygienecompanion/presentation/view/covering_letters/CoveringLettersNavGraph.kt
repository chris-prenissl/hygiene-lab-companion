package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.coveringLettersGraph(
    navController: NavController,
) {
    navigation<Screen.CoveringLetters>(
        startDestination = Screen.CoveringLetters,
    ) {
        composable<Screen.CoveringLetters> {
            CoveringLettersView(onNavigateToDetail = {})
        }
        composable<Screen.CoveringLetterDetail> {
            CoveringLetterDetailView(
                onNavigateUp = navController::navigateUp,
            )
        }
    }
}
