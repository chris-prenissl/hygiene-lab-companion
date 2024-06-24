package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.presentation.util.Route
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.LOGIN

@Composable
fun RegisterView(
    navController: NavController
) {
    Column {
        Text(LOGIN)
        Button(onClick = {
            navController.navigate(Screen.CoveringLetters) {
                popUpTo(Route.LoggedOut) {
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
