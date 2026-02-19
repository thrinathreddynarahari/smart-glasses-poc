package com.ranesvision.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NeonBlue,
    onPrimary = SpaceBlack,
    primaryContainer = NeonBlueDark,
    onPrimaryContainer = NeonBlueGlow,

    secondary = AccentCyan,
    onSecondary = SpaceBlack,
    secondaryContainer = DarkSurface,
    onSecondaryContainer = TextPrimary,

    tertiary = AccentPurple,
    onTertiary = TextPrimary,

    background = SpaceBlack,
    onBackground = TextPrimary,

    surface = DeepNavy,
    onSurface = TextPrimary,
    surfaceVariant = CardSurface,
    onSurfaceVariant = TextSecondary,

    error = ErrorRed,
    onError = SpaceBlack,

    outline = GlassBorder,
    outlineVariant = GlassOverlay
)

@Composable
fun RanesVisionTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = SpaceBlack.toArgb()
            window.navigationBarColor = SpaceBlack.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
