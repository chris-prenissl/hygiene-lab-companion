package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.presentation.util.Screen

@Composable
fun CoveringLettersView(
    navController: NavController
){
    Column {
        Text("Covering Letters View")
        Button(onClick = { navController.navigate(Screen.CoveringLetterDetail.route) }) {
            Text("detail")
        }
    }
}