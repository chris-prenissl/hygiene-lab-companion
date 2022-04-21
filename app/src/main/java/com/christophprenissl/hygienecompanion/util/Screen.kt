package com.christophprenissl.hygienecompanion.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val name: String, val route: String, val icon: ImageVector) {
    object CoveringLetters: Screen("Begleitschein", "covering_letters", Icons.Rounded.Inventory)
    object CoveringLetterBasis: Screen("Basisdaten", "covering_letter_basis", Icons.Rounded.BackupTable)
    object LabWorks: Screen("Labor", "lab_works", Icons.Rounded.Science)
    object Reports: Screen("Befunde", "reports", Icons.Rounded.Summarize)

    object Profile: Screen("Profil", "profile", Icons.Rounded.AccountCircle)
}