package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.view.component.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun MainView() {
    val isLabWorker = false
    val navController = rememberNavController()
    var loggedIn by remember { mutableStateOf(false) }
    val bottomNavItems = listOf(
        if (isLabWorker) Screen.LabWorks else Screen.CoveringLetters,
        Screen.CoveringLetterBasis,
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