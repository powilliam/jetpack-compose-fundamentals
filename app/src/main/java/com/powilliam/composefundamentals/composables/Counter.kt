package com.powilliam.composefundamentals.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme
import com.powilliam.composefundamentals.viewmodels.CounterState

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Counter(uiState: CounterState, onClick: () -> Unit = {}) {
    /*
    * 1. @Composable functions can store a single object in memory by using remember delegating function.
    * 2. It will store the initial value during the composition and return during recomposition
    *
    * val (visible, visibleSet) = remember { mutableStateOf(value = false) }
    * */

    Card(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(all = 8.dp)
                .animateContentSize()
        ) {
            Text(text = "${uiState.value}", style = MaterialTheme.typography.body1)

            AnimatedVisibility(visible = uiState.isPlaceholderVisible) {
                Text(
                    text = "Times you have clicked",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.paddingFromBaseline(top = 8.dp)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview
@Composable
private fun Counter_LightPreview() {
    ComposeFundamentalsTheme {
        Counter(uiState = CounterState(value = 0))
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Counter_DarkPreview() {
    ComposeFundamentalsTheme {
        Counter(uiState = CounterState(value = 0))
    }
}