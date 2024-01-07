package com.example.stockapp.repositories.stock

import android.util.Log
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.integration.firebase.authentication.AuthenticationState
import com.example.stockapp.integration.firebase.database.FirebaseDatabaseConnection
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.serializable.Stock
import com.example.stockapp.serializable.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    private val database: FirebaseDatabaseConnection,
    private val api: StockApi
){

    private val _stocks = MutableStateFlow<List<Stock>>(emptyList())
    val stocks: StateFlow<List<Stock>> = _stocks

    init {
        CoroutineScope(Dispatchers.Default).launch {
            // Fetch stocks from Firebase or cache initially
            fetchStocksFromFirebase()

            // Fetch stocks from API and update the StateFlow
            fetchStocksFromApi()
        }
    }

    private suspend fun fetchStocksFromFirebase() {

        val stocksFromFirebase = database.retrieve(Stock)
        _stocks.value = stocksFromFirebase
    }

    private suspend fun fetchStocksFromApi() {

        try {
            val stocksFromApi = api.fetchStocks()
            _stocks.value = stocksFromApi
        } catch (e: Exception) {
            // Handle API request errors here
            Log.e("StockRepository", "Error fetching stocks from API: ${e.message}")
        }
    }

    fun refreshStocks() {
        // This function can be called to refresh the stocks from the API
        fetchStocksFromApi()
    }

    fun clearCache() {
        database.clearCache()
    }
}

}