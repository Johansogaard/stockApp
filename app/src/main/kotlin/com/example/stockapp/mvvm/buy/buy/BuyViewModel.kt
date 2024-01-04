package com.example.stockapp.mvvm.buy.buy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(BuyUiState())
    val uiState: StateFlow<BuyUiState> = _uiState.asStateFlow()

    var currentAmount by mutableStateOf("")

    var marketPrice = MutableStateFlow(0.0) // LiveData or StateFlow to hold the market price
    val tradingFee = 50

    init {
        marketPrice.value = 1726.88 // Placeholder value
    }
    fun calculateTotalCost(currentAmount: Int): Int {
        return currentAmount + tradingFee
    }
    fun calculateNumberOfShares(currentAmount: Int, marketPrice: Double): Double {
        return (currentAmount / marketPrice)
    }
    fun updateAmount(amount: Int) {

        val newAmount = currentAmount + amount.toString()

        if (newAmount.length <= 7) {
            currentAmount = newAmount

            try {
                if (currentAmount.toInt()+50 > _uiState.value.balance) {
                    _uiState.update { it.copy(isMaxAmount = true) }
                } else {
                    _uiState.update { it.copy(isMaxAmount = false) }
                }
            } catch (e: NumberFormatException) {
                // Handle conversion error
            }
        }

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