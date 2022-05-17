package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.domain.model.entity.Parameter
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun ParametersEditList(
    title: String,
    parameters: List<Parameter>,
    values: List<String>,
    onNumbEdit: (Int, String) -> Unit,
    onTempEdit: (Int, String) -> Unit,
    onBoolEdit: (Int, Boolean) -> Unit,
    onNoteEdit: (Int, String) -> Unit
) {
    Text(title)
    Spacer(modifier = Modifier.padding(vertical = standardPadding))
    parameters.forEachIndexed { idx, parameter ->
        ParameterEdit(
            parameter = parameter,
            value = values[idx],
            onNumbEdit = {
                onNumbEdit(idx, it)
            },
            onTempEdit = {
                onTempEdit(idx, it)
            },
            onBoolEdit = {
                onBoolEdit(idx,it)
            },
            onNoteEdit = {
                onNoteEdit(idx, it)
            }
        )
    }
}
