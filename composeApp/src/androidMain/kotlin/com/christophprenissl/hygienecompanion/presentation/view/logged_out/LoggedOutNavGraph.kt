package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Route
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.loggedOutGraph(
    navController: NavController,
    viewModel: LoggedOutViewModel,
) {
    navigation<Route.LoggedOut>(startDestination = Screen.Login) {
        composable<Screen.Login> {
            val state = viewModel.state.collectAsState()
            LoginView(
                state = state.value,
                onEvent = viewModel::onEvent,
                onLogin = {
                    navController.navigate(Route.CoveringLetters) {
                        popUpTo(Screen.Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Screen.Register> {
            RegisterView(
                navController = navController
            )
        }
    }
}
