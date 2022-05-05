package com.christophprenissl.hygienecompanion.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = UKRBlueLight,
    onPrimary = UKRDarkOcra,
    secondary = UKROrangeLight,
    onSecondary = UKRDarkOcra,
    background = UKRUltraDark,
    onBackground = UKRonUltraDark,
    surface = UKRVeryDark,
    onSurface = UKRBlue,
)

private val LightColorPalette = lightColors(
    primary = UKRBlue,
    onPrimary = UKRDarkPurple,
    secondary = UKROrangeLight,
    onSecondary = UKROcra,
    background = UKRWhite,
    onBackground = UKRUltraLight,
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