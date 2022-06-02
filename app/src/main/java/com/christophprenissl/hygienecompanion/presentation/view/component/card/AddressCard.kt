package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.Address
import com.christophprenissl.hygienecompanion.util.*

@Composable
fun AddressCard(
    address: Address,
    onClick: () -> Unit = {},
    accessAble: Boolean = true
) {
    BasicCard(
        onClick = onClick,
        accessIndicator = accessAble
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
