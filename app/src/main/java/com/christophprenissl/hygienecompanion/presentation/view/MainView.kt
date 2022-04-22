package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.util.COVERING_LETTERS_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.HOME_ROUTE
import com.christophprenissl.hygienecompanion.presentation.view.component.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLetterDetailView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLettersView
import com.christophprenissl.hygienecompanion.presentation.view.lab_works.LabWorkDetailView
import com.christophprenissl.hygienecompanion.presentation.view.lab_works.LabWorksView
import com.christophprenissl.hygienecompanion.presentation.view.profile.ProfileView
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportsView
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun MainView() {
    val navController = rememberNavController()
    var loggedIn by remember { mutableStateOf(false) }
    val bottomNavItems = listOf(
        Screen.CoveringLetters,
        Screen.CoveringLetterBasis,
        Screen.LabWorks,
        Screen.Reports
    )
    Scaffold(
        topBar = {
            if (loggedIn) TopMenuBar(
                navController = navController
            )
        },
        bottomBar = {
            if (loggedIn) BottomNavBar(
                navController = navController,
                navItems = bottomNavItems
            )
        }
    ) {
        NavigationGraph(loggedIn = loggedIn, navController = navController) {
            loggedIn = true
        }
    }
}