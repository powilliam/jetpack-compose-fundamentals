package com.powilliam.composefundamentals

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.composefundamentals.ui.ComposeFundamentalsTheme
import com.powilliam.composefundamentals.viewmodels.CounterEvent
import com.powilliam.composefundamentals.viewmodels.CounterViewModel

class MainActivity : ComponentActivity() {
    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFundamentalsTheme {
                SomeComposableScreen(
                    uiState = counterViewModel.uiState.collectAsState(),
                    onDecrement = { counterViewModel.dispatch(event = CounterEvent.Decrement) },
                    onReset = { counterViewModel.dispatch(event = CounterEvent.Reset) },
                    onIncrement = { counterViewModel.dispatch(event = CounterEvent.Increment) }
                )
            }
        }
    }
}

typealias Callback = () -> Unit

/*
1.  @Composable annotations informs the compiler that it will transform data into UI.
2.  By default, @Composable functions doesn't return anything.
3.  Normally, when a @Composable function returns any data, it's a way to do some calculation.
4.  @Composable functions can receive parameters. They receive it as input and uses it to render UI as output.
*/
@Composable
fun SomeComposableScreen(
    uiState: State<Int>,
    onDecrement: Callback = {},
    onReset: Callback = {},
    onIncrement: Callback = {}
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

@Composable
fun ActionButtons(onDecrement: Callback = {}, onReset: Callback = {}, onIncrement: Callback = {}) {
    Row {
        TextButton(onClick = onDecrement) {
            Text(text = "Decrement")
        }
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = onReset) {
            Text(text = "Reset")
        }
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = onIncrement) {
            Text(text = "Increment")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ActionButtons_LightPreview() {
    ComposeFundamentalsTheme {
        ActionButtons()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ActionButtons_DarkPreview() {
    ComposeFundamentalsTheme {
        ActionButtons()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SomeComposableScreen_LightPreview() {
    ComposeFundamentalsTheme(isDark = false) {
        SomeComposableScreen(uiState = remember { mutableStateOf(0) })
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SomeComposableScreen_DarkPreview() {
    ComposeFundamentalsTheme {
        SomeComposableScreen(uiState = remember { mutableStateOf(0) })
    }
}