package com.bigbigdreams.dreamax.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DreamaxDarkColorScheme = darkColorScheme(
    primary = DreamaxRed,
    onPrimary = DreamaxWhite,
    primaryContainer = DreamaxRedDark,
    onPrimaryContainer = DreamaxWhite,
    secondary = DreamaxMediumGrey,
    onSecondary = DreamaxWhite,
    secondaryContainer = DreamaxDarkGrey,
    onSecondaryContainer = DreamaxOnSurfaceVariant,
    tertiary = DreamaxRedLight,
    onTertiary = DreamaxBlack,
    background = DreamaxBlack,
    onBackground = DreamaxWhite,
    surface = DreamaxSurface,
    onSurface = DreamaxOnSurface,
    surfaceVariant = DreamaxDarkGrey,
    onSurfaceVariant = DreamaxOnSurfaceVariant,
    outline = DreamaxLightGrey,
    error = DreamaxRed,
    onError = DreamaxWhite
)

@Composable
fun DreaMaxTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = DreamaxDarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = DreamaxTypography,
        content = content
    )
}
