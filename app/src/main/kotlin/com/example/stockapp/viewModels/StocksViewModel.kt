package com.example.stockapp.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.yml.charts.common.model.Point
import com.example.stockapp.models.Stock
import com.example.stockapp.models.StockData
import com.example.stockapp.models.StockState

class StocksViewModel : ViewModel() {
    private val _state = mutableStateOf(StockState(mutableListOf()))

    /* State held by this ViewModel
     Note:  The View-Model holds its OWN state.
            This ensures encapsulation, and allows us to
            expose only the state we want to expose to the view.
     */
    val state: State<StockState> get() = _state

    fun addTestStock() {
        val stockData = StockData()
        stockData.pointsData = listOf(
            Point(0f, 40f),
            Point(1f, 90f),
            Point(2f, 0f),
            Point(3f, 60f),
            Point(4f, 10f),)
        stockData.steps = 5
        val newStock = Stock("My New Stock B", 500.00, stockData)
        state.value.stocks.add(newStock)
    }


}
