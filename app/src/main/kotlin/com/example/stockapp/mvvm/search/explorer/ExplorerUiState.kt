package com.example.stockapp.mvvm.search.explorer

import com.example.stockapp.repositories.stock.StockRepository
import com.example.stockapp.serializable.Stock

data class ExplorerUiState (
    val stocks: List<Stock> = emptyList(),
)