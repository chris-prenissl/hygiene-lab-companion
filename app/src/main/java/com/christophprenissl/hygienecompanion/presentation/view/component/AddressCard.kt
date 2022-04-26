package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.domain.model.entity.Address

@Composable
fun AddressCard(address: Address) {
    Column {
        address.name?.let { Text(it) }
        address.zip?.let { Text(it) }
        address.street?.let { Text(it) }
    }
}