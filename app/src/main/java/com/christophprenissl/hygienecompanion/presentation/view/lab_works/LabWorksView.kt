package com.christophprenissl.hygienecompanion.presentation.view.lab_works

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun LabWorksView(
    navController: NavController
) {
    Column {
        Text("Lab Works Detail")
        Button(onClick = {
            navController.navigate(Screen.LabWorkDetail.route)
        }) {
            Text("lab work detail")
        }
    }
}