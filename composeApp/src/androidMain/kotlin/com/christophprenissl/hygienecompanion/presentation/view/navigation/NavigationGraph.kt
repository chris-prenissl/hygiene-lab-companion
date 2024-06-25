package com.christophprenissl.hygienecompanion.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.LoggedOutViewModel
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.LoginView
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(
    isInitiallyLoggedIn: Boolean,
    navItems: List<Screen>,
    navController: NavHostController,
    loggedOutViewModel: LoggedOutViewModel = koinViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = if (isInitiallyLoggedIn) Screen.CoveringLetters else Screen.Login,
    ) {
        composable<Screen.Login> {
            val state = loggedOutViewModel.state.collectAsState()
            LoginView(
                state = state.value,
                onEvent = loggedOutViewModel::onEvent,
                onLogin = {
                    navController.navigate(Screen.CoveringLetters) {
                        popUpTo(Screen.Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Screen.CoveringLetters> {
            LoggedInNavigation(navItems = navItems)
        }
    }
}
