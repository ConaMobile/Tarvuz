package com.conamobile.tarvuz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    //primary button colors
    primary = DefaultGreen,
    //not detected
    primaryVariant = DefaultBlack,
    //not detected
    secondary = DefaultBlack,
    //not detected
    background = DefaultDark,
    //button text color
    onPrimary = Color.White,
    //surface background color
    surface = Color.Black,
    //not detected
    onSecondary = Color.Red,
    //all text content color
    onBackground = DefaultWhite
)

private val LightColorPalette = lightColors(
    //primary button colors
    primary = DefaultGreen,
    //not detected
    primaryVariant = DefaultPink,
    //not detected
    secondary = DefaultGreen60,
    //not detected
    background = Color.White,
    //button text color
    onPrimary = Color.White,
    //surface background color
    surface = DefaultWhite,
    //not detected
    onSecondary = Color.Red,
    //all text content color
    onBackground = DefaultBlack

    /*
    onSurface = Color.Black,
    */
)

@Composable
fun TarvuzTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette
    else LightColorPalette

    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}