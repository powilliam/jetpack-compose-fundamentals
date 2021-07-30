package com.powilliam.composefundamentals.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme

/*
1.  @Composable annotations informs the compiler that it will transform data into UI.
2.  By default, @Composable functions doesn't return anything.
3.  Normally, when a @Composable function returns any data, it's a way to do some calculation.
4.  @Composable functions can receive parameters. They receive it as input and uses it to render UI or resolve calculations as output.
*/
@Composable
fun SomeComposableScreen(
    uiState: State<Int>,
    onDecrement: ActionCallback = {},
    onReset: ActionCallback = {},
    onIncrement: ActionCallback = {}
) {
    Scaffold(
        floatingActionButton = {
            ActionButtons(onDecrement = onDecrement, onReset = onReset, onIncrement = onIncrement)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${uiState.value}", style = MaterialTheme.typography.h6)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SomeComposableScreen_LightPreview() {
    ComposeFundamentalsTheme(isDark = false) {
        SomeComposableScreen(uiState = remember { mutableStateOf(0) })
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SomeComposableScreen_DarkPreview() {
    ComposeFundamentalsTheme {
        SomeComposableScreen(uiState = remember { mutableStateOf(0) })
    }
}