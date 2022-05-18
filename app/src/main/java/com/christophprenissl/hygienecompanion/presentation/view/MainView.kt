package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.view.component.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun MainView() {
    val navController = rememberNavController()
    var loggedIn by remember { mutableStateOf(false) }
    val bottomNavItems = listOf(
        Screen.CoveringLetters,
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
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(
                navController = navController,
                onLogin = {
                    loggedIn = true
                }
            )
        }
    }
}
