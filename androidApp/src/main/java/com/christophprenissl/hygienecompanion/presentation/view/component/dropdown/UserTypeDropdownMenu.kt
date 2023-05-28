package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun UserTypeDropdownMenu(
    value: UserType,
    onUserTypeChoose: (UserType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val userTypes = UserType.values()

    Row {
        DropdownCard(
            onClick = {
                expanded = !expanded
            },
            menuOpen = expanded
        ) {
            Text(value.translation)
        }
        Spacer(modifier = Modifier.padding(horizontal = standardPadding.dp))
        BasicDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            userTypes.forEach {
                BasicDropdownItem(
                    onClick = {
                        onUserTypeChoose(it)
                        expanded = false
                    }
                ) {
                    Text(it.translation)
                }
            }
        }
    }
}
