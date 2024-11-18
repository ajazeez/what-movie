package com.aj.whatmovie.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


@Immutable
data class CustomColorPalette(
    val textColor: Color = Color.Unspecified
)

val LocalCustomColorPalette = staticCompositionLocalOf { CustomColorPalette() }