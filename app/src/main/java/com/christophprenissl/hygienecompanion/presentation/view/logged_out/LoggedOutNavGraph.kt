package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.loggedOutGraph(navController: NavController) {
    navigation(startDestination = Screen.Login.route, route = Screen.Login.graphRoute) {
        composable(Screen.Login.route) {
            LoginView(navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterView(navController = navController)
        }
    }
}
