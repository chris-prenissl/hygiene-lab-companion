package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddressCard(address: Address, onDelete: () -> Unit) {
    val dismissState = rememberDismissState (
        confirmStateChange =  {
            if (it == DismissValue.DismissedToStart) {
                onDelete()
            }
            true
        })
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds =  { FractionalThreshold(dismissalThreshold) },
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                targetValue = if (dismissState.direction != 0f) Color.Red else MaterialTheme.colors.background
            )

            val icon = when(direction) {
                DismissDirection.EndToStart -> Icons.Rounded.Delete
                DismissDirection.StartToEnd -> null
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color),
                contentAlignment = Alignment.CenterEnd
            ) {
                icon?.let {
                    Icon(
                        imageVector =  it,
                        contentDescription = it.name
                    )
                }
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(halfSize)
                .padding(cardPadding),
            elevation = if (dismissState.direction != 0f) 0.dp else standardElevation
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
}
