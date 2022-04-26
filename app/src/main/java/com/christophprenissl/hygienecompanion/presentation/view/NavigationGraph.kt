package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.util.COVERING_LETTERS_ROUTE
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE
import com.christophprenissl.hygienecompanion.util.LOGGED_OUT_ROUTE
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.coveringLetterBasisNavGraph
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.coveringLettersGraph
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.loggedOutGraph
import com.christophprenissl.hygienecompanion.presentation.view.profile.profileNavGraph
import com.christophprenissl.hygienecompanion.presentation.view.reports.reportsNavGraph
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(InternalComposeApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun NavigationGraph(
    loggedIn: Boolean,
    navController: NavHostController,
    coveringLetterBasisViewModel: CoveringLetterBasisViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = if (!loggedIn) LOGGED_OUT_ROUTE else COVERING_LETTERS_ROUTE,
        route = HOME_ROUTE
    ) {
        loggedOutGraph(navController = navController)

        coveringLettersGraph(navController = navController)

        coveringLetterBasisNavGraph(
            navController = navController,
            viewModel = coveringLetterBasisViewModel
        )

        reportsNavGraph(navController = navController)

        profileNavGraph(navController = navController)
    }
}
