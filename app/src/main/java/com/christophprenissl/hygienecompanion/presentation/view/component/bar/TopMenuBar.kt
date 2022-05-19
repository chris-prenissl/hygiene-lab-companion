package com.christophprenissl.hygienecompanion.presentation.view.component.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.R
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun TopMenuBar(navController: NavController) {
    TopAppBar {
        Icon(
            painter = painterResource(id = R.drawable.ic_ukr_logo),
            contentDescription = "UKR Logo",
            Modifier.padding(standardPadding)
        )
        Spacer(modifier = Modifier.fillMaxWidth(0.74f))
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