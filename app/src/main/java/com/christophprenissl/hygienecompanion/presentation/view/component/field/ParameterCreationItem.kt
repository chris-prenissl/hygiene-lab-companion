package com.christophprenissl.hygienecompanion.presentation.view.component.field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.christophprenissl.hygienecompanion.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.model.entity.ParameterType
import com.christophprenissl.hygienecompanion.presentation.view.component.SwipeToDelete
import com.christophprenissl.hygienecompanion.util.PARAMETER
import com.christophprenissl.hygienecompanion.util.parameterCreationItemSize
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.selectableGroup()
        ) {
            ParameterTextField(
                labelText = PARAMETER,
                value = name,
                onValueChange = {
                    name = it
                    item.name = name
                }
            )
            Row{
                options.forEach { type ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(parameterCreationItemSize)
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
                        Text(type.translation)
                    }
                }
            }
        }
    }
}
