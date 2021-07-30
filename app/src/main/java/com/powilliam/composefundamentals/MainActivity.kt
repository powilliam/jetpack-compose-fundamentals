package com.powilliam.composefundamentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.powilliam.composefundamentals.composables.SomeComposableScreen
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