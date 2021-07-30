package com.powilliam.composefundamentals.viewmodels

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Stable
data class CounterState(val value: Int, val isPlaceholderVisible: Boolean = false)

sealed class CounterEvent {
    object Increment : CounterEvent()
    object Decrement : CounterEvent()
    object Reset : CounterEvent()
    object TogglePlaceholderVisibility : CounterEvent()
}

class CounterViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<CounterState> = MutableStateFlow(CounterState(value = 0))
    val uiState: StateFlow<CounterState> = _uiState

    fun dispatch(event: CounterEvent) = viewModelScope.launch {
        when (event) {
            is CounterEvent.Increment -> {
                _uiState.emit(_uiState.value.copy(value = _uiState.value.value.inc()))
            }
            is CounterEvent.Decrement -> {
                _uiState.emit(_uiState.value.copy(value = _uiState.value.value.dec()))
            }
            is CounterEvent.Reset -> {
                _uiState.emit(_uiState.value.copy(value = 0))
            }
            is CounterEvent.TogglePlaceholderVisibility -> {
                _uiState.emit(_uiState.value.copy(isPlaceholderVisible = !_uiState.value.isPlaceholderVisible))
            }
        }
    }
}