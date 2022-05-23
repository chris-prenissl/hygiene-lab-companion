package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.R
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun LoginView(
    navController: NavController,
    viewModel: LoggedOutViewModel,
    onLogin: (UserType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(standardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ukr_logo),
                contentDescription = UKR_LOGO_DESCRIPTION,
                modifier = Modifier.size(titleIconSize)
            )
            Spacer(modifier = Modifier.padding(horizontal = standardPadding))
            TitleText(title = APP_TITLE_START)
        }
        Spacer(modifier = Modifier.padding(vertical = titleDistance))
        Text(LOGIN)
        Spacer(modifier = Modifier.padding(vertical = standardPadding))
        Button(
            onClick = {
                viewModel.login(
                    userType = UserType.HygieneWorker,
                    onLogin = onLogin
                )
                navigateToHomeScreen(navController)
            },
            modifier = Modifier.padding(standardPadding)
        ) {
            Text(AS_HYGIENE_WORKER_REGISTER)
        }
        Button(
            onClick = {
                viewModel.login(
                    userType = UserType.LabWorker,
                    onLogin = onLogin
                )
                navigateToHomeScreen(navController)
            },
            modifier = Modifier.padding(standardPadding)
        ) {
            Text(AS_LAB_WORKER_REGISTER)
        }
        Button(
            onClick = {
                viewModel.login(
                    userType = UserType.Sampler,
                    onLogin = onLogin
                )
                navigateToHomeScreen(navController)
            },
            modifier = Modifier.padding(standardPadding)
        ) {
            Text(AS_SAMPLER_REGISTER)
        }
    }
}

fun navigateToHomeScreen(navController: NavController) {
    navController.popBackStack()
    navController.navigate(Screen.CoveringLetters.graphRoute) {
        popUpTo(HOME_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
