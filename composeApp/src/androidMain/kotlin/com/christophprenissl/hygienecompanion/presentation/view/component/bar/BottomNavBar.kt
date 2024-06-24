package com.christophprenissl.hygienecompanion.presentation.view.component.bar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.christophprenissl.hygienecompanion.presentation.util.Route

@Composable
fun BottomNavBar(
    navController: NavHostController,
    navItems: List<Route>,
    iconsColor: Color = contentColorFor(MaterialTheme.colorScheme.primaryContainer)
) {
    NavigationBar(
        contentColor = iconsColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        navItems.forEach { route ->
            NavigationBarItem(
                icon = { route.icon?.let { Icon(it, contentDescription = route.name) } },
                selected = currentDestination?.hierarchy?.any { it.route == route.name } == true,
                label = { Text(route.name) },
                onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
