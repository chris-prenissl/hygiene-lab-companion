package com.christophprenissl.hygienecompanion.presentation.view.component.field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.util.standardPadding


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ParameterCreationItem(
    item: ParameterBasis,
    onDelete: () -> Unit
) {
    var name by remember { mutableStateOf(item.name?: "") }
    val options = ParameterType.values()
    var checkedRadioButton by remember { mutableStateOf(item.parameterType?: ParameterType.Number) }

    SwipeToDelete(onDelete = onDelete) {
        Column(Modifier.selectableGroup()) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    item.name = name
                }
            )
            Row {
                options.forEach { type ->
                    Column(
                        modifier = Modifier
                            .width(60.dp)
                            .selectable(
                                selected = type == checkedRadioButton,
                                onClick = { checkedRadioButton = type },
                                role = Role.RadioButton
                            )
                            .padding(vertical = standardPadding)
                    ) {
                        RadioButton(
                            selected = type == checkedRadioButton,
                            onClick = {
                                checkedRadioButton = type
                                item.parameterType = checkedRadioButton
                            }
                        )
                        Text(type.name)
                    }
                }
            }
        }
    }
}