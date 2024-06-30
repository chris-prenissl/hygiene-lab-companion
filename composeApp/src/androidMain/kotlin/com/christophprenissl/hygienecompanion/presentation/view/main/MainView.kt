package com.christophprenissl.hygienecompanion.presentation.view.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.navigation.NavigationGraph
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.util.APP_TITLE
import org.koin.androidx.compose.koinViewModel

@SuppressLint("RememberReturnType")
@Composable
fun MainView(mainViewViewModel: MainViewViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val uiState = mainViewViewModel.state.collectAsStateWithLifecycle()

    uiState.value.let { stateValue ->
        if (stateValue.isLoading) {
            Scaffold { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    CircularProgressIndicator()
                }
            }
        } else {
            val userName = stateValue.userName
            val userColor = stateValue.userColor
            Scaffold(
                topBar = {
                    TopMenuBar(
                        title = userName ?: APP_TITLE,
                        hasLogoutButton = stateValue.userType != null,
                        onLogout = {
                            mainViewViewModel.onEvent(MainViewEvent.Logout)
                            navController.navigate(Screen.Login) {
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
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavigationGraph(
                        isInitiallyLoggedIn = stateValue.initiallyLoggedIn,
                        navItems = stateValue.mainNavItems,
                        navController = navController,
                    )
                }
            }
        }
    }
}
