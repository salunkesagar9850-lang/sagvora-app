package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = CyanNeon,
  onPrimary = Color(0xFF041017),
  primaryContainer = Color(0xFF003D4C),
  onPrimaryContainer = Color(0xFFB8F3FF),

  secondary = OrangeNeon,
  onSecondary = Color(0xFF260D00),
  secondaryContainer = Color(0xFF522100),
  onSecondaryContainer = Color(0xFFFFD1B3),

  tertiary = VioletNeon,
  onTertiary = Color.White,
  tertiaryContainer = Color(0xFF381475),
  onTertiaryContainer = Color(0xFFE3D4FF),

  background = DarkBackground,
  onBackground = TextPrimary,
  surface = DarkSurface,
  onSurface = TextPrimary,
  surfaceVariant = DarkSurfaceVariant,
  onSurfaceVariant = TextSecondary,
  outline = BorderGlass,
  outlineVariant = Color(0xFF1B2436),
  surfaceContainer = DarkCardSurface
)

private val LightColorScheme = lightColorScheme(
  primary = Color(0xFF00778A),
  onPrimary = Color.White,
  primaryContainer = Color(0xFFBCEEF8),
  onPrimaryContainer = Color(0xFF002026),

  secondary = Color(0xFFD65600),
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFFFDBC8),
  onSecondaryContainer = Color(0xFF341000),

  tertiary = Color(0xFF673AB7),
  onTertiary = Color.White,

  background = LightBackground,
  onBackground = LightTextPrimary,
  surface = LightSurface,
  onSurface = LightTextPrimary,
  surfaceVariant = LightSurfaceVariant,
  onSurfaceVariant = LightTextSecondary,
  outline = LightBorder,
  outlineVariant = Color(0xFFE2E8F0),
  surfaceContainer = Color(0xFFF1F5F9)
)

@Composable
fun SagvoraTheme(
  darkTheme: Boolean = true, // Dark-first interface
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

