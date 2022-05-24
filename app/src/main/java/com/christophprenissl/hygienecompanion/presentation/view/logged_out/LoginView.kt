package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.R
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicCheckBoxField
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.dropdown.UserTypeDropdownMenu
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginView(
    navController: NavController,
    viewModel: LoggedOutViewModel,
    onLogin: (UserType) -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var hasCertificate by rememberSaveable { mutableStateOf(false) }
    var isSamplerOfInstitute by rememberSaveable { mutableStateOf(false) }
    var userType by rememberSaveable { mutableStateOf<UserType?>(null) }

    viewModel.user.value?.let { user ->
        name = TextFieldValue(user.name ?: "")
        hasCertificate = user.hasCertificate ?: false
        isSamplerOfInstitute = user.isSamplerOfInstitute ?: false
        userType = user.userType ?: UserType.Sampler
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(standardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        stickyHeader {
            Text(LOGIN)
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = doubleStandardPadding))
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
        }
        item {
            BasicSurface(
                border = BorderStroke(basicBorderStroke, MaterialTheme.colors.onBackground)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(standardPadding)
                ) {
                    ParameterTextField(
                        labelText = USER_NAME,
                        value = name,
                        onValueChange = {
                            name = it
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    if (userType != UserType.LabWorker) {
                        BasicCheckBoxField(
                            title = HAS_CERTIFICATE,
                            value = hasCertificate,
                            onCheckedChange = {
                                hasCertificate = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        BasicCheckBoxField(
                            title = QM_SAMPLER,
                            value = isSamplerOfInstitute,
                            onCheckedChange = {
                                isSamplerOfInstitute = it
                            }
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding))
                    }

                    UserTypeDropdownMenu(onUserTypeChoose = {
                        userType = it
                    })
                }
            }
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }
        item {
            Button(
                enabled = userType != null,
                onClick = {
                    viewModel.login(
                        name = name.text,
                        hasCertificate = hasCertificate,
                        isSamplerOfInstitute = isSamplerOfInstitute,
                        userType = userType!!,
                        onLogin = onLogin
                    )
                    navigateToHomeScreen(navController)
                },
                modifier = Modifier.padding(standardPadding)
            ) {
                Text(LOGIN)
            }
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
