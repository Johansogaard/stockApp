package com.example.stockapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stockapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_black),
    Font(R.font.roboto_black_italic),
    Font(R.font.roboto_bold),
    Font(R.font.roboto_bold_italic),
    Font(R.font.roboto_italic),
    Font(R.font.roboto_light),
    Font(R.font.roboto_light_italic),
    Font(R.font.roboto_medium),
    Font(R.font.roboto_medium_italic),
    Font(R.font.roboto_regular),
    Font(R.font.roboto_thin),
    Font(R.font.roboto_thin_italic)
)