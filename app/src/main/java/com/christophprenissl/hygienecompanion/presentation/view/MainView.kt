package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE

@Composable
fun MainView() {
    val navController = rememberNavController()
    var userType by rememberSaveable { mutableStateOf("") }

    val bottomNavItems = when(userType) {
        UserType.Sampler.name -> {
            listOf(Screen.CoveringLetters)
        }
        UserType.LabWorker.name -> {
            listOf(Screen.Lab)
        }
        UserType.HygieneWorker.name -> {
            listOf(
                Screen.CoveringLetters,
                Screen.CoveringLetterBasis,
                Screen.Reports
            )
        }
        else -> emptyList()
    }
    Scaffold(
        topBar = {
            if (userType.isNotEmpty()) TopMenuBar(
                navController = navController
            )
        },
        bottomBar = {
            if (userType.isNotEmpty()) BottomNavBar(
                navController = navController,
                navItems = bottomNavItems
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (userType.isNotEmpty()) {
                navController.popBackStack()
                navController.navigate(Screen.CoveringLetters.graphRoute) {
                    popUpTo(HOME_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            NavigationGraph(
                navController = navController,
                onLogin = {
                    userType = it.name
                }
            )
        }
    }
}
