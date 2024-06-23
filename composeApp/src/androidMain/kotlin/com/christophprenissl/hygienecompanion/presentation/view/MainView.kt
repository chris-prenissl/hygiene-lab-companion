package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.MainViewEvent
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.APP_TITLE

@Composable
fun MainView(mainViewViewModel: MainViewViewModel) {
    val navController = rememberNavController()

    val uiState = mainViewViewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.value.userType) {
        if (uiState.value.userType != null) {
            navController.navigate(Screen.CoveringLetters.graphRoute) {
                popUpTo(Screen.Login.graphRoute) {
                    inclusive = true
                }
            }
        }
    }

    val stateValue = uiState.value
    val userName = stateValue.userName
    val userColor = stateValue.userColor
    Scaffold(
        topBar = {
            TopMenuBar(
                title = userName ?: APP_TITLE,
                hasLogoutButton = stateValue.userType != null,
                onLogout = {
                    mainViewViewModel.onEvent(MainViewEvent.Logout)
                },
                hasBackButton = navController.previousBackStackEntry != null,
                onNavigateUp = navController::navigateUp,
                titleColor = userColor
            )
        },
        bottomBar = {
            val navItems = uiState.value.bottomNavItems
            if (navItems.isNotEmpty()) {
                BottomNavBar(
                    navController = navController,
                    navItems = navItems,
                    iconsColor = userColor
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(
                navController = navController,
            )
        }
    }
}
