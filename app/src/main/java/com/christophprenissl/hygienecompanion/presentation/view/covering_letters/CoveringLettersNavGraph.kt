package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.COVERING_LETTERS_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.coveringLettersGraph(navController: NavController) {
    navigation(startDestination = Screen.CoveringLetters.route, route = COVERING_LETTERS_ROUTE) {
        composable(Screen.CoveringLetters.route) {
            CoveringLettersView(navController = navController)
        }
        composable(Screen.CoveringLetterDetail.route) {
            CoveringLetterDetailView()
        }
    }
}