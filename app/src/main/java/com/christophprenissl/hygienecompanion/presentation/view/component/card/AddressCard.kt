package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun AddressCard(
    address: Address,
    onClick: () -> Unit
) {
    BasicCard(
        onClick = onClick,
        accessIndicator = true
    ) {
        Column {
            address.name?.let { Text(it) }
            Row {
                Text(CONTACT)
                address.contactName?.let { Text(it) }
            }
            address.city?.let { Text(it) }
            address.street?.let { Text(it) }
        }
    }
}
