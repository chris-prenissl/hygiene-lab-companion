package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.christophprenissl.hygienecompanion.presentation.util.COVERING_LETTERS_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.HOME_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.LOGGED_OUT_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.coveringLettersGraph
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.loggedOutGraph
import com.christophprenissl.hygienecompanion.presentation.view.profile.ProfileView
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportsView

@Composable
fun NavigationGraph(loggedIn: Boolean, navController: NavHostController, onClick: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = if (!loggedIn) LOGGED_OUT_ROUTE else COVERING_LETTERS_ROUTE,
        route = HOME_ROUTE
    ) {
        loggedOutGraph(navController = navController, onClick = onClick)

        coveringLettersGraph(navController = navController)

        composable(Screen.CoveringLetterBasis.route) {
            CoveringLetterBasisView()
        }
        composable(Screen.Reports.route) {
            ReportsView()
        }
        composable(Screen.Profile.route) {
            ProfileView()
        }
    }
}


