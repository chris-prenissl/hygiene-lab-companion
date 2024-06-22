package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.ui.theme.hygieneWorkerColor
import com.christophprenissl.hygienecompanion.presentation.ui.theme.labWorkerColor
import com.christophprenissl.hygienecompanion.presentation.ui.theme.samplerColor
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.APP_TITLE
import com.christophprenissl.hygienecompanion.util.DataStoreUser

@Composable
fun MainView(dataStoreUser: DataStoreUser) {
    val defaultColor = contentColorFor(MaterialTheme.colorScheme.surface)
    val navController = rememberNavController()
    var topBarTitle by rememberSaveable { mutableStateOf(APP_TITLE) }
    var topBarTitleColor by remember { mutableStateOf(Color.White) }
    var bottomNavItems by rememberSaveable { mutableStateOf<List<Screen>>(emptyList()) }
    LaunchedEffect(Unit) {
        dataStoreUser.getUserType().collect { userType ->
            bottomNavItems = when(userType) {
                UserType.Sampler -> {
                    listOf(Screen.CoveringLetters)
                }
                UserType.LabWorker -> {
                    listOf(Screen.Lab)
                }
                UserType.HygieneWorker -> {
                    listOf(
                        Screen.CoveringLetters,
                        Screen.CoveringLetterBasis,
                        Screen.Reports
                    )
                }
                else -> emptyList()
            }

            topBarTitleColor = when(userType) {
                UserType.Sampler -> samplerColor
                UserType.LabWorker -> labWorkerColor
                UserType.HygieneWorker -> hygieneWorkerColor
                else -> defaultColor
            }
            topBarTitle = userType?.translation ?: APP_TITLE

            if (userType != null) {
                navController.navigate(Screen.CoveringLetters.route)
            }
        }
    }

    Scaffold(
        topBar = {
            TopMenuBar(
                title = topBarTitle,
                navController = navController,
                titleColor = topBarTitleColor
            )
        },
        bottomBar = {
            if (bottomNavItems.isNotEmpty()) BottomNavBar(
                navController = navController,
                navItems = bottomNavItems,
                iconsColor = topBarTitleColor
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(
                navController = navController,
            )
        }
    }
}
