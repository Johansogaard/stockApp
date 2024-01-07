package com.example.stockapp.repositories.stock

import android.util.Log
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.integration.firebase.database.FirebaseDatabaseConnection
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.serializable.Stock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    private val database: FirebaseDatabaseConnection,
    private val api: StockApi,
    private val authentication: Authentication,
){



    private val _selectedStock = MutableStateFlow(Stock())
    val selectedStock: StateFlow<Stock> = _selectedStock


    suspend fun fetchUserStocksFromFirebase(): List<Stock>? {
        val stockDefault: HashMap<String, Stock> = hashMapOf()
        val stocksFromFirebase = database.retrieve(path = "users", refPath = listOf(authentication.state.value.userId, "portfolio", "stocks"), stockDefault)

        //_stocks.value = stocksFromFirebase
        return null
    }


    suspend fun fetchStockFromApi(symbol: String): Stock? {
        try {
            return api.retrieveStock(symbol, "", "")

        } catch (e: Exception) {
            // Handle API request errors here
            Log.e("StockRepository", "Error fetching stocks from API: ${e.message}")
            return null
        }
    }


    suspend fun fetchStocksBySymbols(symbols: List<String>): List<Stock> {
        try {
            val fetchedStocks = mutableListOf<Stock>()

            for (symbol in symbols) {
                val stockFromApi = fetchStockFromApi(symbol)
                stockFromApi?.let {
                    fetchedStocks.add(it)
                }
            }

            return fetchedStocks
        } catch (e: Exception) {
            // Handle API request errors here
            Log.e("StockRepository", "Error fetching stocks by symbols from API: ${e.message}")
            return emptyList()
        }
    }

    suspend fun fetchStocksFromApi(): List<Stock> {
        try {
            val batchSize = 100
            var offset = 0
            val fetchedStocks = mutableListOf<Stock>()

            while (true) {
                val batchStocks = fetchBatchStocksFromApi(batchSize, offset)
                if (batchStocks.isEmpty()) {
                    break
                }

                fetchedStocks.addAll(batchStocks)
                offset += batchSize
            }

            /*
            _selectedStocks.update {
                it.copy(value = fetchedStocks)
            } */

            return fetchedStocks
        } catch (e: Exception) {
            // Handle API request errors here
            Log.e("StockRepository", "Error fetching stocks from API: ${e.message}")
            return emptyList()
        }
    }

    private suspend fun fetchBatchStocksFromApi(batchSize: Int, offset: Int): List<Stock> {
        try {
            val batchStocks = api.retrieveAnyListOfStocks(batchSize.toString(), offset.toString(), "", "")
            return batchStocks ?: emptyList()
        } catch (e: Exception) {
            // Handle API request errors here
            Log.e("StockRepository", "Error fetching batch of stocks from API: ${e.message}")
            return emptyList()
        }
    }


    fun setSelectedStock(stock: Stock) {
        _selectedStock.value = stock
    }








    fun refreshStocks() {
        // This function can be called to refresh the stocks from the API

    }

    fun clearCache() {
        database.clearCache()
    }
}

