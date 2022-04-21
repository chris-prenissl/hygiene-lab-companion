package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.util.Screen

@Composable
fun TopMenuBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = { expanded = true }
            ) {
                Icon(Icons.Rounded.MoreVert, "Menu")
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            navController.navigate(Screen.Profile.route)
                        }
                    ) {
                        Row {
                            Icon(Screen.Profile.icon, Screen.Profile.name)
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(Screen.Profile.name)
                        }
                    }
                }
            }
        }
    }
}