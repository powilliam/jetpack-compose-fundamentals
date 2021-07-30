package com.powilliam.composefundamentals.ui

import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp

data class Elevations(val topAppBar: Dp)

val LocalElevations =
    compositionLocalOf { Elevations(topAppBar = AppBarDefaults.TopAppBarElevation) }