package com.christophprenissl.hygienecompanion.presentation.view.component.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.R
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun TopMenuBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ukr_logo),
                    contentDescription = UKR_LOGO_DESCRIPTION
                )
                Text(
                    text = APP_TITLE,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = standardPadding)
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = BACK_BUTTON_DESCRIPTION
                )
            }
        }
    )
}
