package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.standardPadding
import org.intellij.lang.annotations.JdkConstants

@Composable
fun TopMenuBar(navController: NavController) {
    TopAppBar {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Profile.route)
                }
            ) {
                Screen.Profile.let {
                    Text(it.name)
                    Spacer(modifier = Modifier.padding(horizontal = standardPadding))
                    it.icon?.let { icon ->
                        Icon(icon, Screen.Profile.name)
                    }
                }
            }
        }
    }
}