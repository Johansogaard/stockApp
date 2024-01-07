package com.example.stockapp.repositories.stock

import android.util.Log
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.integration.firebase.authentication.AuthenticationState
import com.example.stockapp.integration.firebase.database.FirebaseDatabaseConnection
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.serializable.Stock
import com.example.stockapp.serializable.User
import io.finnhub.api.models.SymbolLookup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    private val database: FirebaseDatabaseConnection,
    private val api: StockApi,
    private val authentication: Authentication,
){



    private val _selectedStocks = MutableStateFlow<Stock>(Stock())
    val selectedStock: StateFlow<Stock> = _selectedStocks

    /*
    init {
        CoroutineScope(Dispatchers.Default).launch {
            // Fetch stocks from Firebase or cache initially
            //fetchStocksFromFirebase()

            // Fetch stocks from API and update the StateFlow
            //fetchStocksFromApi()
        }
    }

     */


    suspend fun fetchUserStocksFromFirebase(): List<Stock>? {
        val stockDefault: HashMap<String, Stock> = hashMapOf()
        val stocksFromFirebase = database.retrieve(path = "users", refPath = listOf(authentication.state.value.userId, "portfolio", "stocks"), stockDefault)

        //_stocks.value = stocksFromFirebase
        return null
    }


    suspend fun fetchStockFromApi(symbol: String): Stock? {
        try {
            val stocksFromApi = api.retrieveStock(symbol, "", "")

            return stocksFromApi
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








    fun refreshStocks() {
        // This function can be called to refresh the stocks from the API

    }

    fun clearCache() {
        database.clearCache()
    }
}

}