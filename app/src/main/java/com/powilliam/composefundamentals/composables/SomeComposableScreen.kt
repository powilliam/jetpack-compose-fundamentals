package com.powilliam.composefundamentals.composables

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.composefundamentals.R
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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SomeComposableScreen(
    modifier: Modifier = Modifier,
    uiState: State<CounterState>,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onDecrement: ActionCallback = {},
    onReset: ActionCallback = {},
    onIncrement: ActionCallback = {},
    onClickCounterCard: ActionCallback = {}
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        },
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
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Counter(uiState = uiState.value, onClick = onClickCounterCard)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SomeComposableScreen_Preview() {
    ComposeFundamentalsTheme(isDark = false) {
        SomeComposableScreen(uiState = remember { mutableStateOf(CounterState(value = 0)) })
    }
}