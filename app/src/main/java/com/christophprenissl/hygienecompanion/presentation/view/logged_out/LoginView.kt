package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
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
        verticalArrangement = Arrangement.Center
    ) {
        Text(LOGIN)
        Button(
            onClick = {
                viewModel.login(
                    userType = UserType.HygieneWorker,
                    onLogin = onLogin
                )
            }
        ) {
            Text(AS_HYGIENE_WORKER_REGISTER)
        }
        Button(
            onClick = {
                viewModel.login(
                    userType = UserType.LabWorker,
                    onLogin = onLogin
                )
            }
        ) {
            Text(AS_LAB_WORKER_REGISTER)
        }
        Button(
            onClick = {
                viewModel.login(
                    userType = UserType.Sampler,
                    onLogin = onLogin
                )
            }
        ) {
            Text(AS_SAMPLER_REGISTER)
        }
    }
}
