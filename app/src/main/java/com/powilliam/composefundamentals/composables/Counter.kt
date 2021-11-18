package com.powilliam.composefundamentals.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme
import com.powilliam.composefundamentals.viewmodels.CounterState

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication = LocalIndication.current,
    shape: Shape = RoundedCornerShape(percent = 25),
    tonalElevation: Dp = 2.dp,
    uiState: CounterState,
    onClick: () -> Unit = {}
) {
    /*
    * 1. @Composable functions can store a single object in memory by using remember delegating function.
    * 2. It will store the initial value during the composition and return during recomposition.
    * 3. Remember doesn't survive during configuration changes, only recompositions. Instead, you must use rememberSaveable
    * 4. ViewModels survives to configuration changes so @Composable functions that observes or collects some state doesn't need to implement any saver
    *
    * val (visible, visibleSet) = remember { mutableStateOf(value = false) }
    * */

    Surface(
        modifier = modifier,
        shape = shape,
        tonalElevation = tonalElevation
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .clickable(interactionSource, indication) {
                    onClick()
                }
                .animateContentSize()
                .padding(all = 16.dp)
        ) {
            Text(text = "${uiState.value}", style = MaterialTheme.typography.bodyLarge)
            AnimatedVisibility(visible = uiState.isPlaceholderVisible) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "Times you have clicked",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Counter_Preview() {
    ComposeFundamentalsTheme {
        Column {
            Counter(uiState = CounterState(value = 0))
            Counter(uiState = CounterState(value = 0, isPlaceholderVisible = true))
        }
    }
}