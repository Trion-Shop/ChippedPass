package com.megapari.chippedpass.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ChipBlue,
    onPrimary = ChipDarkBg,
    secondary = ChipRed,
    onSecondary = ChipWhite,
    tertiary = ChipBlueBright,
    background = ChipDarkBg,
    surface = ChipDarkCard,
    onSurface = ChipWhite
)

@Composable
fun ChippedPassTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
