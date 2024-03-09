package com.christophprenissl.hygienecompanion.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = UKRPrimaryDark,
    onPrimary = UKROnPrimaryDark,
    secondary = UKRSecondaryDark,
    onSecondary = UKROnSecondaryDark,
    background = UKRBackgroundDark,
    onBackground = UKROnBackgroundDark,
    surface = UKRSurfaceDark,
    onSurface = UKROnSurfaceDark,
)

private val LightColorPalette = lightColorScheme(
    primary = UKRPrimaryLight,
    onPrimary = UKROnPrimaryLight,
    secondary = UKRSecondaryLight,
    onSecondary = UKROnSecondaryLight,
    background = UKRBackgroundLight,
    onBackground = UKROnBackgroundLight
)

@Composable
fun HygieneCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
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
        content = content
    )
}