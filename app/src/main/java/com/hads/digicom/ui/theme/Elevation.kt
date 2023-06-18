package com.hads.digicom.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation(
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val samiLarge: Dp = 24.dp,
    val large: Dp = 32.dp,
)

val localElevation = compositionLocalOf { Elevation() }
val MaterialTheme.roundedElevation: Elevation
    @Composable
    @ReadOnlyComposable
    get() = localElevation.current