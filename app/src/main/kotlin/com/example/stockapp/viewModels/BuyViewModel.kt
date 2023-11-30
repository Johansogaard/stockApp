package com.example.stockapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stockapp.models.BuyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BuyUiState())
    val uiState: StateFlow<BuyUiState> = _uiState.asStateFlow()

    var currentAmount by mutableStateOf("0")
        private set

    fun updateAmount(amount: Int) {
        if (currentAmount == "0") {
            currentAmount = ""
        }

        currentAmount += amount

        if (currentAmount.toInt() >= _uiState.value.balance) {
            // error
            _uiState.update { currentState ->
                currentState.copy(
                    isMaxAmount = true,
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    isMaxAmount = false,
                )
            }
        }
    }

    fun setAmount() {
        if (currentAmount != "0")
        _uiState.update { currentState ->
            currentState.copy(
                isMaxAmount = false,
                amount = currentAmount.toInt()
            )
        }
    }

}