package com.christophprenissl.hygienecompanion.presentation.view.component.dropdown


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun UserTypeDropdownMenu(
    onUserTypeChoose: (UserType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val userTypes = UserType.values()
    var chosenUserType by remember { mutableStateOf(UserType.Sampler) }

    Row {
        DropdownCard(
            onClick = {
                expanded = !expanded
            },
            menuOpen = expanded
        ) {
            Text(chosenUserType.name)
        }
        Spacer(modifier = Modifier.padding(horizontal = standardPadding))
        BasicDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            userTypes.forEach {
                BasicDropdownItem(
                    onClick = {
                        chosenUserType = it
                        onUserTypeChoose(it)
                        expanded = false
                    }
                ) {
                    Text(it.name)
                }
            }
        }
    }
}
