package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.cardPadding
import com.christophprenissl.hygienecompanion.util.halfSize
import com.christophprenissl.hygienecompanion.util.iconSize
import com.christophprenissl.hygienecompanion.util.standardPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasicCard(
    onClick: () -> Unit,
    accessIndicator: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(halfSize)
            .padding(cardPadding),
        onClick = onClick
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(standardPadding),
            content = content
        )
        if (accessIndicator) {
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
                        .size(iconSize)
                )
            }
        }
    }
}

@Composable
fun BasicCard(
    content: @Composable RowScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(halfSize)
            .padding(cardPadding)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(standardPadding),
            content = content
        )
    }
}
