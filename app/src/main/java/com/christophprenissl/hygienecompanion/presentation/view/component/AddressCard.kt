package com.christophprenissl.hygienecompanion.presentation.view.component

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
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddressCard(
    address: Address,
    onClick: () -> Unit
) {
        Card(
            modifier = Modifier
                .fillMaxWidth(halfSize)
                .padding(cardPadding),
            onClick = onClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    address.name?.let { Text(it) }
                    address.zip?.let { Text(it) }
                    address.street?.let { Text(it) }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowRight,
                    contentDescription = Icons.Rounded.ArrowRight.name,
                    modifier = Modifier
                        .size(64.dp)
                )
            }
        }
}
