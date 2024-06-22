package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE
import com.christophprenissl.hygienecompanion.util.LOGGED_OUT_ROUTE
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.coveringLetterBasisNavGraph
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLettersViewModel
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.coveringLettersGraph
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.LoggedOutViewModel
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.loggedOutGraph
import com.christophprenissl.hygienecompanion.presentation.view.profile.profileNavGraph
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportsViewModel
import com.christophprenissl.hygienecompanion.presentation.view.reports.reportsNavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    coveringLetterBasisViewModel: CoveringLetterBasisViewModel = getViewModel(),
    coveringLettersViewModel: CoveringLettersViewModel = getViewModel(),
    reportsViewModel: ReportsViewModel = getViewModel(),
    loggedOutViewModel: LoggedOutViewModel = getViewModel(),
    onLogin: (UserType) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = LOGGED_OUT_ROUTE,
        route = HOME_ROUTE
    ) {
        loggedOutGraph(
            navController = navController,
            viewModel = loggedOutViewModel,
            onLogin = onLogin
        )

        coveringLettersGraph(
            navController = navController,
            viewModel = coveringLettersViewModel
        )

        coveringLetterBasisNavGraph(
            navController = navController,
            viewModel = coveringLetterBasisViewModel
        )

        reportsNavGraph(
            navController = navController,
            viewModel = reportsViewModel
        )

        profileNavGraph()
    }
}
