package com.christophprenissl.hygienecompanion.presentation.view.lab_works

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.LAB_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.labWorksNavGraph(navController: NavController) {
    navigation(startDestination = Screen.LabWorks.route, route = LAB_ROUTE) {
        composable(Screen.LabWorks.route) {
            LabWorksView(navController = navController)
        }
        composable(Screen.LabWorkDetail.route) {
            LabWorkDetailView()
        }
    }
}