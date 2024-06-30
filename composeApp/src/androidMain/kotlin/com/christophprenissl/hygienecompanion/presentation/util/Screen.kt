package com.christophprenissl.hygienecompanion.presentation.util

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BackupTable
import androidx.compose.material.icons.rounded.Plagiarism
import androidx.compose.material.icons.rounded.Summarize
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface Screen : Parcelable {
    val name: String get() = this.javaClass.simpleName

    val icon: ImageVector? get() = when (this) {
        CoveringLetters -> Icons.Rounded.Plagiarism
        CoveringLetterBasis -> Icons.Rounded.BackupTable
        Reports -> Icons.Rounded.Summarize
        else -> null
    }

    @Parcelize
    @Serializable
    data object Login : Screen

    @Parcelize
    @Serializable
    data object Register : Screen

    @Parcelize
    @Serializable
    data object CoveringLetters : Screen

    @Parcelize
    @Serializable
    data object CoveringLetterDetail : Screen

    @Parcelize
    @Serializable
    data object CoveringLetterBasis : Screen

    @Parcelize
    @Serializable
    data object BasisDetail : Screen

    @Parcelize
    @Serializable
    data object SampleLocations : Screen

    @Parcelize
    @Serializable
    data object CoveringLetterSeriesDetail : Screen

    @Parcelize
    @Serializable
    data object Reports : Screen

    @Parcelize
    @Serializable
    data object ReportDetail : Screen

    @Parcelize
    @Serializable
    data object Profile : Screen
}
