package com.powilliam.composefundamentals.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CounterEvent {
    object Increment : CounterEvent()
    object Decrement : CounterEvent()
    object Reset : CounterEvent()
}

class CounterViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<Int> = MutableStateFlow(0)
    val uiState: StateFlow<Int> = _uiState

    fun dispatch(event: CounterEvent) = viewModelScope.launch {
        when (event) {
            is CounterEvent.Increment -> {
                _uiState.emit(_uiState.value.inc())
            }
            is CounterEvent.Decrement -> {
                _uiState.emit(_uiState.value.dec())
            }
            is CounterEvent.Reset -> {
                _uiState.emit(0)
            }
        }
    }
}