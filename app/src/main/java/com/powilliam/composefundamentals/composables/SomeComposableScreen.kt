package com.powilliam.composefundamentals.composables

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme
import com.powilliam.composefundamentals.viewmodels.CounterState

/*
1.  @Composable annotations informs the compiler that it will transform data into UI.
2.  By default, @Composable functions doesn't return anything.
3.  Normally, when a @Composable function returns any data, it's a way to do some calculation.
4.  @Composable functions can receive parameters. They receive it as input and uses it to render UI or resolve calculations as output.
5.  In fact, when a @Composable function receives another input,
    they will be re-rendered from scratch. This process is called RECOMPOSITION.
6.  @Composable functions are flexible to the Kotlin language. We can use since if else statements to for loops.
*/

/*
* 1. @Composable functions can execute in any order
* 2. @Composable functions can execute in parallel
* 3. @Composable functions can be skyped (normally when its parent suffers recompositions but they doesn't need to be redraw)
* */
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SomeComposableScreen(
    uiState: State<CounterState>,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onDecrement: ActionCallback = {},
    onReset: ActionCallback = {},
    onIncrement: ActionCallback = {},
    onClickCounterCard: ActionCallback = {}
) {
    val isModuleOfFive by remember(uiState) { derivedStateOf { listOf(-5, 5).contains(uiState.value.value) } }

    /*
    * 1. rememberUpdateState will receive a value and always refer to the latest value calculated during recomposition. Opposite to React useMemo and useCallback
    *    val currentOnDecrement by rememberUpdatedState(newValue = onDecrement)
    *    val currentOnReset by rememberUpdatedState(newValue = onReset)
    *    val currentOnIncrement by rememberUpdatedState(newValue = onIncrement)
    *    val currentOnClickCounterCard by rememberUpdatedState(newValue = onClickCounterCard)
    * */

    if (!uiState.value.isPlaceholderVisible && isModuleOfFive) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "You have clicked ${uiState.value.value} times",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            ActionButtons(
                onDecrement = onDecrement,
                onReset = onReset,
                onIncrement = onIncrement
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Counter(uiState = uiState.value, onClick = onClickCounterCard)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SomeComposableScreen_LightPreview() {
    ComposeFundamentalsTheme(isDark = false) {
        SomeComposableScreen(uiState = remember { mutableStateOf(CounterState(value = 0)) })
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SomeComposableScreen_DarkPreview() {
    ComposeFundamentalsTheme {
        SomeComposableScreen(uiState = remember { mutableStateOf(CounterState(value = 0)) })
    }
}