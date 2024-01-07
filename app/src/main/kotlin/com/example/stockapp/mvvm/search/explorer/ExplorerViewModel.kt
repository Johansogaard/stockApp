package com.example.stockapp.mvvm.search.explorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapp.mvvm.order.OrderUiState
import com.example.stockapp.repositories.stock.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExplorerUiState())
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()


    fun fetchStocks() {

        viewModelScope.launch {
            val fetchedStocks = stockRepository.fetchStocksFromApi()
            _uiState.value = _uiState.value.copy(stocks = fetchedStocks)
        }
    }
    fun change() {

        _uiState.update { currentState ->
            currentState.copy()
        }
    }


}