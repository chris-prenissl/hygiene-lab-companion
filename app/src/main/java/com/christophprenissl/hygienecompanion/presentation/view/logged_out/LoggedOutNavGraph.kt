package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.LOGGED_OUT_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.loggedOutGraph(navController: NavController, onClick: () -> Unit) {
    navigation(startDestination = Screen.Login.route, route = LOGGED_OUT_ROUTE) {
        composable(Screen.Login.route) {
            LoginView(onClick = onClick)
        }
        composable(Screen.Register.route) {
            RegisterView(navController = navController)
        }
    }
}