package com.christophprenissl.hygienecompanion.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = appPrimaryDark,
    onPrimary = appOnPrimaryDark,
    secondary = appSecondaryDark,
    onSecondary = appOnSecondaryDark,
    background = appBackgroundDark,
    onBackground = appOnBackgroundDark,
    surface = appSurfaceDark,
    onSurface = appOnSurfaceDark,
)

private val LightColorPalette = lightColorScheme(
    primary = appPrimaryLight,
    onPrimary = appOnPrimaryLight,
    secondary = appSecondaryLight,
    onSecondary = appOnSecondaryLight,
    background = appBackgroundLight,
    onBackground = appOnBackgroundLight,
)

@Composable
fun HygieneCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
