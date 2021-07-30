package com.powilliam.composefundamentals.ui

import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp

data class Elevations(val topAppBar: Dp)

/*
* 1. With compositionLocalOf: changing the value provided during recomposition invalidates only the content that reads its current value
* 2. With staticCompositionLocalOf: changing the value provided during recomposition invalidates EVERYTHING
* */
val LocalElevations =
    compositionLocalOf { Elevations(topAppBar = AppBarDefaults.TopAppBarElevation) }