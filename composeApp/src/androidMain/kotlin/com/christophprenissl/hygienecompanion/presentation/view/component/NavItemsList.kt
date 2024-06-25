package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun NavItemsList(
    screenList: List<Screen>,
    onNavigate: (Screen) -> Unit,
) {
    Column {
        screenList.forEach { screen ->
            Button(onClick = { onNavigate(screen) }) {
                Row {
                    screen.icon?.let {
                        Image(
                            imageVector = it,
                            contentDescription = it.name,
                            modifier = Modifier.padding(end = standardPadding.dp)
                        )
                    }
                    Text(text = screen.name)
                }
            }
        }
    }
}