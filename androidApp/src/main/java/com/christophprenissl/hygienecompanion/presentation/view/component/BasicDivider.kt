package com.christophprenissl.hygienecompanion.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.util.doubleStandardPadding
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun BasicDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = doubleStandardPadding)
    )
}
