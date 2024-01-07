package com.example.stockapp.mvvm.stock

import com.example.stockapp.serializable.Stock

data class StockUiState(
    val stock: Stock = Stock(),
    val stockData: StockData = StockData()
)