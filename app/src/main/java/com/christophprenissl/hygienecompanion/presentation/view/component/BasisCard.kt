package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.util.cardPadding
import com.christophprenissl.hygienecompanion.util.halfSize

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasisCard(
    basis: Basis,
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
                basis.description?.let { Text(it) }
                basis.norm?.let { Text(it) }
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