package com.powilliam.composefundamentals.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Light = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryVariant = Teal700,
    onSecondary = Color.Black
)
val Dark = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    onPrimary = Color.Black,
    secondary = Teal200,
    secondaryVariant = Teal200,
    onSecondary = Color.Black
)

@Composable
fun ComposeFundamentalsTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!isDark) Light else Dark
    val elevations = if (!isDark) {
        Elevations(topAppBar = AppBarDefaults.TopAppBarElevation)
    } else {
        Elevations(topAppBar = 0.dp)
    }

    MaterialTheme(colors = colors, shapes = shapes, typography = typography) {
        CompositionLocalProvider(LocalElevations provides elevations) {
            content()
        }
    }
}