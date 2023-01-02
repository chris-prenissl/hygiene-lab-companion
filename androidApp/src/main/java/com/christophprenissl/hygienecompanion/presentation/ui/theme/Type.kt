package com.christophprenissl.hygienecompanion.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.christophprenissl.hygienecompanion.android.R

val fonts = FontFamily(
    Font(R.font.librefranklin_bold, weight = FontWeight.Bold),
    Font(R.font.librefranklin_medium, weight = FontWeight.Normal),
    Font(R.font.librefranklin_thin, weight = FontWeight.Thin),
    Font(R.font.librefranklin_italic, style = FontStyle.Italic)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic
    )
)
