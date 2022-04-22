package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun LoginView(
    onClick: () -> Unit
) {
    Column {
        Text("login")
        Button(onClick = onClick) {
            Text("login")
        }
    }
}