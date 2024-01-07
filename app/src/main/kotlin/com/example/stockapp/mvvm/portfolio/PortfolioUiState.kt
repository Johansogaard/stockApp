package com.example.stockapp.mvvm.portfolio

import com.example.stockapp.serializable.Stock

data class PortfolioUiState (
    val stocks: List<Stock> = emptyList()
)