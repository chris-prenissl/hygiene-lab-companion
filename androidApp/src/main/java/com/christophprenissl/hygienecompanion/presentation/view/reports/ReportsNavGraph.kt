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
    navigation(startDestination = Screen.Reports.route, route = Screen.Reports.graphRoute) {
        composable(Screen.Reports.route) {
            ReportsView(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(Screen.ReportDetail.route) {
            ReportDetailView(viewModel = viewModel)
        }
    }
}
