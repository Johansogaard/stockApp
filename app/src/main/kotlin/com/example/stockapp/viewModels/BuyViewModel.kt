package com.example.stockapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stockapp.models.BuyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyViewModel(private val initialUiState: BuyUiState = BuyUiState()) : ViewModel() {
    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<BuyUiState> = _uiState.asStateFlow()

    var currentAmount by mutableStateOf("")


    fun updateAmount(amount: Int) {
        Log.d("BuyViewModel", "Before update: currentAmount = $currentAmount")

        val newAmount = currentAmount + amount.toString()

        if (newAmount.length <= 7) {
            currentAmount = newAmount

            try {
                if (currentAmount.toInt() > _uiState.value.balance) {
                    _uiState.update { it.copy(isMaxAmount = true) }
                } else {
                    _uiState.update { it.copy(isMaxAmount = false) }
                }
            } catch (e: NumberFormatException) {
                // Handle conversion error
            }
        }
        Log.d("BuyViewModel", "updateAmount called with: $amount")

    }
    private fun updateUiState() {
        try {
            val amount = currentAmount.toIntOrNull() ?: 0
            if (amount > _uiState.value.balance) {
                _uiState.update { it.copy(isMaxAmount = true) }
            } else {
                _uiState.update { it.copy(isMaxAmount = false) }
            }
        } catch (e: NumberFormatException) {
            // Handle conversion error
        }
    }
    fun removeLastDigit() {
        if (currentAmount.isNotEmpty()) {
            currentAmount = currentAmount.dropLast(1)
            updateUiState()
        }
    }
    fun setAmount() {
        try {
            if (currentAmount.isNotEmpty()) {
                val amount = currentAmount.toInt()
                _uiState.update { it.copy(isMaxAmount = false, amount = amount) }
            }
        } catch (e: NumberFormatException) {
            // Handle error if conversion fails
        }

}}