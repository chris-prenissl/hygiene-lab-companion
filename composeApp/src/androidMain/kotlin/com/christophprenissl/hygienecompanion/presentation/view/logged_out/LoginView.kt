package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.android.R
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicCheckBoxField
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface
import com.christophprenissl.hygienecompanion.presentation.view.component.TitleText
import com.christophprenissl.hygienecompanion.presentation.view.component.dropdown.UserTypeDropdownMenu
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterTextField
import com.christophprenissl.hygienecompanion.util.*

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginView(
    state: LoggedOutState = LoggedOutState(),
    onEvent: (LoggedOutEvent) -> Unit = {},
    onLogin: () -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(standardPadding.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(vertical = doubleStandardPadding.dp),
    ) {
        stickyHeader {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_lens_blur_24),
                    contentDescription = APP_LOGO_DESCRIPTION,
                    modifier = Modifier.size(titleIconSize.dp),
                )
                TitleText(title = APP_TITLE_START)
            }
        }

        item {
            BasicSurface(
                border = BorderStroke(basicBorderStroke.dp, MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.padding(vertical = standardPadding.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(standardPadding.dp),
                ) {
                    Text("Login")
                    ParameterTextField(
                        labelText = USER_NAME,
                        value = state.name ?: "",
                        onValueChange = { onEvent(LoggedOutEvent.UserNameChanged(it)) },
                    )
                    Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                    if (state.userType != UserType.LabWorker) {
                        BasicCheckBoxField(
                            title = HAS_CERTIFICATE,
                            value = state.hasCertificate,
                            onCheckedChange = {
                                onEvent(LoggedOutEvent.SetCertificateChanged(it))
                            },
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                        BasicCheckBoxField(
                            title = QM_SAMPLER,
                            value = state.isUserOfInstitute,
                            onCheckedChange = {
                                onEvent(LoggedOutEvent.SetSamplerOfInstituteChanged(it))
                            },
                        )
                        Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
                    }
                    UserTypeDropdownMenu(
                        value = state.userType ?: UserType.Sampler,
                        onUserTypeChoose = {
                            onEvent(LoggedOutEvent.UserTypeChanged(it))
                        },
                    )
                }
            }
        }
        item {
            Button(
                onClick = {
                    onEvent(LoggedOutEvent.Login)
                    onLogin()
                },
                modifier = Modifier.padding(standardPadding.dp),
            ) {
                Text(LOGIN)
            }
        }
    }
}

@Preview
@Composable
fun LoginViewPreview() {
    LoginView()
}
