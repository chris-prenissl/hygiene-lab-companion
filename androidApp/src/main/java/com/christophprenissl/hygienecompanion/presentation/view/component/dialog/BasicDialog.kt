package com.christophprenissl.hygienecompanion.presentation.view.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.christophprenissl.hygienecompanion.presentation.view.component.BasicSurface
import com.christophprenissl.hygienecompanion.util.standardPadding

@Composable
fun BasicDialog(
    onDismissRequest: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        BasicSurface {
            Box(contentAlignment = Alignment.Center) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(standardPadding.dp),
                    content = content
                )
            }
        }
    }
}
