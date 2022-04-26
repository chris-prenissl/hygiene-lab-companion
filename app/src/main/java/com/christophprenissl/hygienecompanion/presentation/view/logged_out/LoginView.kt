package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun LoginView(navController: NavController) {
    Column {
        Text("login")
        Button(onClick = { navController.navigate(Screen.CoveringLetters.graphRoute) }) {
            Text("login")
        }
    }
}
