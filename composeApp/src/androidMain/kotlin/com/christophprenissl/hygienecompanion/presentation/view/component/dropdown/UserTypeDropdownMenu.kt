package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun UserTypeDropdownMenu(
    value: UserType,
    onUserTypeChoose: (UserType) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }
    val userTypes = UserType.entries.toTypedArray()

    Row {
        DropdownCard(
            onClick = {
                focusManager.clearFocus()
                expanded = !expanded
            },
            menuOpen = expanded,
        ) {
            Text(value.translation)
        }
        Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
        BasicDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            userTypes.forEach {
                BasicDropdownItem(
                    onClick = {
                        expanded = false
                        onUserTypeChoose(it)
                    },
                ) {
                    Text(it.translation)
                }
            }
        }
    }
}
