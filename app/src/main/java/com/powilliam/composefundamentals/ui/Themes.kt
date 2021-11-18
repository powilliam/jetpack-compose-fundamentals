package com.powilliam.composefundamentals.ui

import android.content.Context
import android.os.Build
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Purple500,
    onPrimary = Color.White,
    secondary = Teal200,
    onSecondary = Color.Black
)
private val DarkColorScheme = darkColorScheme(
    primary = Purple200,
    onPrimary = Color.Black,
    secondary = Teal200,
    onSecondary = Color.Black
)

@Composable
fun ComposeFundamentalsTheme(
    context: Context = LocalContext.current,
    isDark: Boolean = isSystemInDarkTheme(),
    isDynamicColorSchemeSupported: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    ripple: Indication = rememberRipple(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isDark && isDynamicColorSchemeSupported -> dynamicDarkColorScheme(context)
        !isDark && isDynamicColorSchemeSupported -> dynamicLightColorScheme(context)
        isDark && !isDynamicColorSchemeSupported -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(colorScheme = colorScheme, typography = typography) {
        CompositionLocalProvider(LocalIndication provides ripple) {
            content()
        }
    }
}