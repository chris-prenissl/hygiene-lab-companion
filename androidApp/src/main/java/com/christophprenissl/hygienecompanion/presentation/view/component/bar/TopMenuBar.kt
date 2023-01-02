package com.christophprenissl.hygienecompanion.presentation.view.component.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.android.R
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun TopMenuBar(
    title: String = APP_TITLE,
    titleColor: Color = contentColorFor(backgroundColor),
    navController: NavController
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(standardPadding)
                    .offset(x = iconBarOffset),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ukr_logo),
                    contentDescription = UKR_LOGO_DESCRIPTION
                )
                Text(
                    text = title,
                    color = titleColor,
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
