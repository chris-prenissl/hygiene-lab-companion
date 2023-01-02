package com.christophprenissl.hygienecompanion.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

private val DarkColorPalette = darkColors(
    primary = UKRPrimaryDark,
    primaryVariant = UKRPrimaryDark,
    onPrimary = UKROnPrimaryDark,
    secondary = UKRSecondaryDark,
    secondaryVariant = UKRSecondaryDark,
    onSecondary = UKROnSecondaryDark,
    background = UKRBackgroundDark,
    onBackground = UKROnBackgroundDark,
    surface = UKRSurfaceDark,
    onSurface = UKROnSurfaceDark,
)

private val LightColorPalette = lightColors(
    primary = UKRPrimaryLight,
    primaryVariant = UKRPrimaryLight,
    onPrimary = UKROnPrimaryLight,
    secondary = UKRSecondaryLight,
    secondaryVariant = UKRSecondaryLight,
    onSecondary = UKROnSecondaryLight,
    background = UKRBackgroundLight,
    onBackground = UKROnBackgroundLight
)

@Composable
fun HygieneCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}