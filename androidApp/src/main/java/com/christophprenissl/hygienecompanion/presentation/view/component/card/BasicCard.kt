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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.cardPadding
import com.christophprenissl.hygienecompanion.util.halfSize
import com.christophprenissl.hygienecompanion.util.iconSize
import com.christophprenissl.hygienecompanion.util.standardPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasicCard(
    onClick: () -> Unit,
    accessIndicator: Boolean = true,
    indicatorIcon: ImageVector = Icons.Rounded.ArrowRight,
    elevation: Dp = 0.dp,
    content: @Composable RowScope.() -> Unit
) {
    Card(
        elevation = elevation,
        modifier = Modifier
            .fillMaxWidth(halfSize)
            .padding(cardPadding.dp),
        onClick = onClick
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(standardPadding.dp),
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
                    imageVector = indicatorIcon,
                    contentDescription = indicatorIcon.name,
                    modifier = Modifier
                        .size(iconSize.dp)
                )
            }
        }
    }
}

@Composable
fun BasicCard(
    elevation: Dp = 0.dp,
    content: @Composable RowScope.() -> Unit
) {
    Card(
        elevation = elevation,
        modifier = Modifier
            .fillMaxWidth(halfSize)
            .padding(cardPadding.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(standardPadding.dp),
            content = content
        )
    }
}
