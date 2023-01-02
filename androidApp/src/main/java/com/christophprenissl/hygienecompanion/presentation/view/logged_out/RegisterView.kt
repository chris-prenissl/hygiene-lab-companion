package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.util.HOME_ROUTE
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.LOGIN

@Composable
fun RegisterView(
    navController: NavController
) {
    Column {
        Text(LOGIN)
        Button(onClick = {
            navController.navigate(Screen.CoveringLetters.route) {
                popUpTo(HOME_ROUTE) {
                    saveState = true
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }) {
            Text(LOGIN)
        }
    }
}
