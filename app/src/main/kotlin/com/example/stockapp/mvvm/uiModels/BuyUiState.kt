package com.example.stockapp.mvvm.uiModels

data class BuyUiState(
    val amount: Int = 0,
    val balance: Int = 24555, // Test Value
    val isMaxAmount: Boolean = false,
)