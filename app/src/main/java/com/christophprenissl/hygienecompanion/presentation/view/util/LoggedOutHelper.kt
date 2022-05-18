package com.christophprenissl.hygienecompanion.presentation.view.util

import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.DataStoreUserType
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE

suspend fun loginAs(
    userTypeStore: DataStoreUserType,
    userType: UserType,
    onLogin: () -> Unit,
    navController: NavController
) {
    userTypeStore.saveUserType(userType)
    onLogin()
    navController.popBackStack()
    navController.navigate(Screen.CoveringLetters.graphRoute) {
        popUpTo(HOME_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
