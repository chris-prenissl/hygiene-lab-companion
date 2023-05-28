package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.util.doubleStandardPadding

@Composable
fun BasicDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = doubleStandardPadding.dp)
    )
}
