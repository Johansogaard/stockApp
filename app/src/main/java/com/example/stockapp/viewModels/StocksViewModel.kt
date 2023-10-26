package com.example.stockapp.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stockapp.models.Stock
import com.example.stockapp.models.StockState

class StockViewModel : ViewModel() {
    private val _state = mutableStateOf(StockState())

    /* State held by this ViewModel
     Note:  The View-Model holds its OWN state.
            This ensures encapsulation, and allows us to
            expose only the state we want to expose to the view.
     */
    val state: State<StockState> get() = _state

    fun updateStocks(newStocks: List<Stock>) {
        _state.value = _state.value.copy(stocks = newStocks)
    }

}
