package com.example.stockapp.mvvm.stock

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.stockapp.repositories.stock.StockRepository
import com.example.stockapp.serializable.History
import com.example.stockapp.serializable.Stock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StockUiState())
    val state: StateFlow<StockUiState> = _state.asStateFlow()

    private val stockState: StateFlow<Stock> = stockRepository.selectedStock

    init{
        observeStockState()
    }

    private fun observeStockState() {
        viewModelScope.launch {
            stockState.collect { stock ->
                onStockStateChanged(stock)
            }
        }
    }

    fun addTestStock() {
        val stockData = StockData()
        stockData.pointsData = listOf(
            Point(0f, 40f),
            Point(1f, 90f),
            Point(2f, 0f),
            Point(3f, 60f),
            Point(4f, 10f),)
        stockData.steps = 5
        val newStock = Stock(symbol = "NVO", company_name = "Novo Nordisk")
        _state.update { currentState ->
            currentState.copy(
                //stock = newStock,
                stockData = stockData
            )
        }
    }

    fun testCallSetStockToNVO() {
        viewModelScope.launch {
            stockRepository.setSelectedStock(stockRepository.fetchStockFromApi("NVO")!!)
        }
    }

    fun convertDatesToPoints(history: Map<String, History>) {

    }

    fun onStockStateChanged(stock: Stock) {
        _state.update { currentState ->
            currentState.copy(stock = stock)
        }
        Log.i("StockViewModel", "Stock changed to: $stock")
    }
}