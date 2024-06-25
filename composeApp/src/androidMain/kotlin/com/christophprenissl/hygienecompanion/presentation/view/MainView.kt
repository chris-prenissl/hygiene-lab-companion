package com.christophprenissl.hygienecompanion.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.MainViewEvent
import com.christophprenissl.hygienecompanion.presentation.util.Route
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.util.APP_TITLE

@SuppressLint("RememberReturnType")
@Composable
fun MainView(mainViewViewModel: MainViewViewModel) {
    val navController = rememberNavController()
    val uiState = mainViewViewModel.state.collectAsStateWithLifecycle()

    uiState.value?.let { stateValue ->
        val userName = stateValue.userName
        val userColor = stateValue.userColor
        Scaffold(
            topBar = {
                TopMenuBar(
                    title = userName ?: APP_TITLE,
                    hasLogoutButton = stateValue.userType != null,
                    onLogout = {
                        mainViewViewModel.onEvent(MainViewEvent.Logout)
                        navController.navigate(Route.LoggedOut) {
                            navController.currentBackStackEntry?.destination?.route?.let {
                                popUpTo(it) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    hasBackButton = false,
                    onNavigateUp = navController::navigateUp,
                    titleColor = userColor
                )
            },
            bottomBar = {
                val navItems = stateValue.bottomNavItems
                if (navItems.isNotEmpty()) {
                    BottomNavBar(
                        navController = navController,
                        navItems = navItems,
                        iconsColor = userColor
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(
                    isInitiallyLoggedIn = stateValue.initiallyLoggedIn,
                    navController = navController,
                )
            }
        }
    } ?: Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            CircularProgressIndicator()
        }
    }
}
