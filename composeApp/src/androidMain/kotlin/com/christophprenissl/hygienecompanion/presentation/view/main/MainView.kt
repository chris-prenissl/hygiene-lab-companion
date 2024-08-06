package com.christophprenissl.hygienecompanion.presentation.view.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.view.navigation.NavigationGraph
import com.christophprenissl.hygienecompanion.util.APP_TITLE

@SuppressLint("RememberReturnType")
@Composable
fun MainView(
    state: MainViewState = MainViewState(),
    onEvent: (MainViewEvent) -> Unit = {},
) {
    val navController = rememberNavController()

    if (state.isLoading) {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                CircularProgressIndicator()
            }
        }
    } else {
        val userName = state.userName
        val userColor = state.userColor
        Scaffold(
            topBar = {
                TopMenuBar(
                    title = userName ?: APP_TITLE,
                    hasLogoutButton = state.userType != null,
                    onLogout = {
                        onEvent(MainViewEvent.Logout)
                        navController.navigate(Screen.Login) {
                            navController.currentBackStackEntry?.destination?.route?.let {
                                popUpTo(it) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    hasBackButton = false,
                    onNavigateUp = navController::navigateUp,
                    titleColor = userColor,
                )
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(
                    isInitiallyLoggedIn = state.initiallyLoggedIn,
                    navItems = state.mainNavItems,
                    navController = navController,
                )
            }
        }
    }
}
