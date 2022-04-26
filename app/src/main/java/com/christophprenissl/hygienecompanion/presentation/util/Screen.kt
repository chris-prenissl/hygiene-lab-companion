package com.christophprenissl.hygienecompanion.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.christophprenissl.hygienecompanion.util.*

sealed class Screen(val name: String, val graphRoute: String, val route: String, val icon: ImageVector?) {
    object Login: Screen("Login", LOGGED_OUT_ROUTE, "login", null)
    object Register: Screen("Registrieren", LOGGED_OUT_ROUTE, "register", null)

    object CoveringLetters: Screen("Begleitscheine", COVERING_LETTERS_ROUTE, "covering_letters", Icons.Rounded.Biotech)
    object LabWorks: Screen("Labor", LAB_ROUTE, "lab", Icons.Rounded.Science)
    object CoveringLetterDetail: Screen("Begleitschein", COVERING_LETTERS_ROUTE, "covering_letter_detail", null)

    object CoveringLetterBasis: Screen("Basisdaten", COVERING_LETTER_BASIS_ROUTE, "covering_letters_basis", Icons.Rounded.BackupTable)

    object Reports: Screen("Befunde", REPORTS_ROUTE, "reports", Icons.Rounded.Summarize)

    object Profile: Screen("Profil", PROFILE_ROUTE,  "profile", Icons.Rounded.AccountCircle)

}