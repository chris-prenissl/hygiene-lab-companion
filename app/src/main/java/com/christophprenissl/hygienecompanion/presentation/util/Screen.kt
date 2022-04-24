package com.christophprenissl.hygienecompanion.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val name: String, val route: String, val icon: ImageVector?) {
    object Login: Screen("Login", "login", null)
    object Register: Screen("Registrieren", "register", null)

    object CoveringLetters: Screen("Begleitscheine", "covering_letters", Icons.Rounded.Inventory)
    object CoveringLetterBasis: Screen("Basisdaten", "covering_letter_basis", Icons.Rounded.BackupTable)
    object Reports: Screen("Befunde", "reports", Icons.Rounded.Summarize)

    object Profile: Screen("Profil", "profile", Icons.Rounded.AccountCircle)

    object CoveringLetterDetail: Screen("Begleitschein", "covering_letter_detail", null)
}