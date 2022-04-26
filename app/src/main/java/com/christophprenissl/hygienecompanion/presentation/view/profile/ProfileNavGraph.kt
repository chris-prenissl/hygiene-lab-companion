package com.christophprenissl.hygienecompanion.presentation.view.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    navigation(startDestination = Screen.Profile.route, route = Screen.Profile.graphRoute) {
        composable(Screen.Profile.route) {
            ProfileView()
        }
    }
}