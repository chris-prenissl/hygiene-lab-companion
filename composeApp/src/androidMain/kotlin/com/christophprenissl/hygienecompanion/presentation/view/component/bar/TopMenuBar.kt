package com.christophprenissl.hygienecompanion.presentation.view.component.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.android.R
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenuBar(
    title: String = APP_TITLE,
    titleColor: Color = contentColorFor(MaterialTheme.colorScheme.background),
    hasBackButton: Boolean = false,
    hasLogoutButton: Boolean = false,
    onLogout: () -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(standardPadding.dp)
                    .offset(x = iconBarOffset.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_lens_blur_24),
                    contentDescription = UKR_LOGO_DESCRIPTION
                )
                Text(
                    text = title,
                    color = titleColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = standardPadding.dp)
                )
            }
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(
                    onClick = onNavigateUp
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = BACK_BUTTON_DESCRIPTION
                    )
                }
            }
        },
        actions = {
            if (hasLogoutButton) {
                IconButton(
                    onClick = onLogout
                ) {
                    Icon(
                        painter = painterResource(id = com.google.firebase.database.collection.R.drawable.googleg_standard_color_18),
                        contentDescription = "Logout",
                        tint = Color.Red
                    )
                }
            }
        }
    )
}
