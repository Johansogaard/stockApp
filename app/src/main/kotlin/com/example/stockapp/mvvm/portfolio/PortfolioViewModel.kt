package com.example.stockapp.mvvm.portfolio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stockapp.mvvm.order.OrderUiState
import com.example.stockapp.repositories.stock.StockRepository
import com.example.stockapp.serializable.Stock
import com.example.stockapp.stockApi.getStockData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(Stock())
    val uiState: StateFlow<Stock> = _uiState.asStateFlow()

    /*private val _stockData = MutableStateFlow<Stock>()
    val stockData: StateFlow<Stock> = _stockData */

    fun refreshStockData() {
        val stock =
    }


}