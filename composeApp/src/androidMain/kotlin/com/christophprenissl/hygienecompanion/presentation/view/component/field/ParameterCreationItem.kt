package com.christophprenissl.hygienecompanion.presentation.view.component.field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.util.PARAMETER
import com.christophprenissl.hygienecompanion.util.parameterCreationItemSize
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun ParameterCreationItem(
    item: ParameterBasis,
    onDelete: () -> Unit,
) {
    var name by remember { mutableStateOf(item.name) }
    val options = ParameterType.entries.toTypedArray()
    var checkedRadioButton by remember { mutableStateOf(item.parameterType) }

    SwipeToDelete(onDelete = onDelete) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.selectableGroup(),
        ) {
            ParameterTextField(
                labelText = PARAMETER,
                value = name,
                onValueChange = {
                    name = it
                    item.name = name
                },
            )
            Row {
                options.forEach { type ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(parameterCreationItemSize.dp)
                            .selectable(
                                selected = type == checkedRadioButton,
                                onClick = { checkedRadioButton = type },
                                role = Role.RadioButton,
                            )
                            .padding(vertical = standardPadding.dp),
                    ) {
                        RadioButton(
                            selected = type == checkedRadioButton,
                            onClick = {
                                checkedRadioButton = type
                                item.parameterType = checkedRadioButton
                            },
                        )
                        Text(type.translation)
                    }
                }
            }
        }
    }
}
