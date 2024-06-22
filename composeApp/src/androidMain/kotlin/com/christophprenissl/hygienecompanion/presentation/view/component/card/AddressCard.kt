package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
            Text(address.name)
            Row {
                Text(CONTACT)
                Text(address.contactName)
            }
            Text(address.city)
            Text(address.street)
        }
    }
}
