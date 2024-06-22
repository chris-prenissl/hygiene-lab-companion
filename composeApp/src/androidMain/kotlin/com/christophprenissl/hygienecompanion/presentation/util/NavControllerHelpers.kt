package com.christophprenissl.hygienecompanion.presentation.util

import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE

fun NavController.navigateToHomeScreen() {
    popBackStack()
    navigate(Screen.CoveringLetters.graphRoute) {
        popUpTo(HOME_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}