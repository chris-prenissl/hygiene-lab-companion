package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import android.annotation.SuppressLint
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.loggedOutGraph(
    navController: NavController,
    viewModel: LoggedOutViewModel,
) {
    navigation(startDestination = Screen.Login.route, route = Screen.Login.graphRoute) {
        composable(Screen.Login.route) {
            val state = viewModel.state.collectAsState()
            LoginView(
                state = state.value,
                onEvent = viewModel::onEvent
            )
        }
        composable(Screen.Register.route) {
            RegisterView(
                navController = navController
            )
        }
    }
}
