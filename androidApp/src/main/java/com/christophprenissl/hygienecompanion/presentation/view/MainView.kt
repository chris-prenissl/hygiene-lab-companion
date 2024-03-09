package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.ui.theme.hygieneWorkerColor
import com.christophprenissl.hygienecompanion.presentation.ui.theme.labWorkerColor
import com.christophprenissl.hygienecompanion.presentation.ui.theme.samplerColor
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.bar.TopMenuBar
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.APP_TITLE

@Composable
fun MainView() {
    val navController = rememberNavController()
    var userType by rememberSaveable { mutableStateOf<UserType?>(null) }

    val bottomNavItems = when(userType) {
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
    val topBarTitle = userType?.translation ?: APP_TITLE
    val topBarTitleColor = when(userType) {
        UserType.Sampler -> samplerColor
        UserType.LabWorker -> labWorkerColor
        UserType.HygieneWorker -> hygieneWorkerColor
        else -> contentColorFor(MaterialTheme.colorScheme.surface)
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
            if (userType != null) BottomNavBar(
                navController = navController,
                navItems = bottomNavItems,
                iconsColor = topBarTitleColor
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(
                navController = navController,
                onLogin = {
                    userType = it
                }
            )
        }
    }
}
