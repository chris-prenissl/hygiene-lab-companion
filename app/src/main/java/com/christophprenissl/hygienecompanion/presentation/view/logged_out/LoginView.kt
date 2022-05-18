package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.util.loginAs
import com.christophprenissl.hygienecompanion.util.*
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    navController: NavController,
    viewModel: LoggedOutViewModel,
    onLogin: () -> Unit
) {
    val userTypeStore = DataStoreUserType(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

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
                coroutineScope.launch {
                    loginAs(
                        userTypeStore = userTypeStore,
                        userType = UserType.HygieneWorker,
                        onLogin = onLogin,
                        navController = navController
                    )
                }
            }
        ) {
            Text(AS_HYGIENE_WORKER_REGISTER)
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    loginAs(
                        userTypeStore = userTypeStore,
                        userType = UserType.LabWorker,
                        onLogin = onLogin,
                        navController = navController
                    )
                }
            }
        ) {
            Text(AS_LAB_WORKER_REGISTER)
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    loginAs(
                        userTypeStore = userTypeStore,
                        userType = UserType.Sampler,
                        onLogin = onLogin,
                        navController = navController
                    )
                }
            }
        ) {
            Text(AS_SAMPLER_REGISTER)
        }
    }
}
