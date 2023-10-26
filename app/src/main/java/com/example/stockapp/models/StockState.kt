package com.example.stockapp.models

data class StockState(
    val stocks: List<Stock> = emptyList(),
)

data class Stock(
    val name: String,
    val price: Double,
)