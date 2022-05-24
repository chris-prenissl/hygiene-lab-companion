package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.domain.model.entity.User
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.util.NAME

@Composable
fun LabWorkerCard(
    user: User
) {
    BasicCard {
        Column {
            ParameterText(
                title = NAME,
                value = user.name
            )
        }
    }
}
