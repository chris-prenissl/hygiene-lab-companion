package com.christophprenissl.hygienecompanion.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BackupTable
import androidx.compose.material.icons.rounded.Plagiarism
import androidx.compose.material.icons.rounded.Summarize
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Screen {
    val name : String get() = this.javaClass.simpleName

    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object CoveringLetters : Screen

    @Serializable
    data object CoveringLetterDetail : Screen

    @Serializable
    data object CoveringLetterBasis : Screen

    @Serializable
    data object BasisDetail : Screen

    @Serializable
    data object SampleLocations : Screen

    @Serializable
    data object CoveringLetterSeriesDetail : Screen

    @Serializable
    data object Reports : Screen

    @Serializable
    data object ReportDetail : Screen

    @Serializable
    data object Profile : Screen
}

sealed interface Route {
    val icon : ImageVector? get() = when (this) {
        CoveringLetters -> Icons.Rounded.Plagiarism
        CoveringLetterBasis -> Icons.Rounded.BackupTable
        Reports -> Icons.Rounded.Summarize
        else -> null
    }

    val name : String get() = this.javaClass.simpleName

    @Serializable
    data object LoggedOut : Route

    @Serializable
    data object CoveringLetters : Route

    @Serializable
    data object CoveringLetterBasis : Route

    @Serializable
    data object Reports : Route
}
