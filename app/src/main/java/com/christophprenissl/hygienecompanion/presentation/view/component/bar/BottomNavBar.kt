package com.christophprenissl.hygienecompanion.presentation.view.component.bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun BottomNavBar(
    navController: NavHostController,
    navItems: List<Screen>
) {
    BottomNavigation {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            navItems.forEach { screen ->
                BottomNavigationItem(
                    icon = { screen.icon?.let { Icon(it, contentDescription = screen.name) } },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    label = { Text(screen.name) },
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(screen.graphRoute) {
                            popUpTo(HOME_ROUTE) {
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
}