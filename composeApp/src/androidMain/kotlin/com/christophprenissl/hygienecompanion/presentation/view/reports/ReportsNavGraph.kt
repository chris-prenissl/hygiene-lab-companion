package com.christophprenissl.hygienecompanion.presentation.view.reports

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.christophprenissl.hygienecompanion.presentation.util.Screen

fun NavGraphBuilder.reportsNavGraph(
    navController: NavController,
    viewModel: ReportsViewModel
) {
    navigation<Screen.Reports>(startDestination = Screen.Reports) {
        composable<Screen.Reports> {
            ReportsView(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable<Screen.ReportDetail> {
            ReportDetailView(viewModel = viewModel)
        }
    }
}
