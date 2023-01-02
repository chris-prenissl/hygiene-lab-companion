package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.christophprenissl.hygienecompanion.util.SWIPE_THRESHOLD


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDelete(
    deleteState: DismissState = rememberDismissState (
        confirmStateChange =  {
            if (it == DismissValue.DismissedToStart) {
                onDelete()
            }
            true
        }),
    onDelete: () -> Unit,
    disMissContent: @Composable() (RowScope.() -> Unit)
) {
    if (deleteState.currentValue != DismissValue.Default) {
        LaunchedEffect(Unit) {
            deleteState.reset()
        }
    }
    SwipeToDismiss(
        state = deleteState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(SWIPE_THRESHOLD) },
        background = {
            val color by animateColorAsState(
                targetValue = when(deleteState.dismissDirection) {
                    DismissDirection.EndToStart -> Color.Red
                    else -> Color.Transparent
                }
            )

            val icon = when(deleteState.dismissDirection) {
                DismissDirection.EndToStart -> Icons.Rounded.Delete
                else -> null
            }
            icon?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector =  it,
                        contentDescription = it.name
                    )
                }
            }
        },
        dismissContent = disMissContent
    )
}
