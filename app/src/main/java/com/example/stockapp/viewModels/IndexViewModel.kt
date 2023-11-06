package com.example.stockapp.viewModels

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapp.models.CompetitionState
import com.example.stockapp.screens.activeButton
import io.finnhub.api.apis.DefaultApi
import com.example.stockapp.stockApi.FinnhubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


class IndexViewModel : ViewModel() {

    /*private val _state = mutableStateOf(CompetitionState())
    val state: State<CompetitionState> get() = _state

    // A simple methods - This is how (and where) we would change the state
    fun updateScore(newScore: Int) {
        _state.value = _state.value.copy(score = newScore)
    }*/

    private val apiClient = FinnhubApi.getClient()
    data class StockInfo(
        val symbol: String,
        val currentPrice: String,
        val previousClosePrice: String
    )

    private val _usaStocks = mutableStateOf<List<StockInfo>>(listOf())
    val usaStocks: State<List<StockInfo>> = _usaStocks

    fun fetchUSAStocks(context: Context) {
        viewModelScope.launch {
            // Use Dispatchers.IO for network requests
            withContext(Dispatchers.IO) {
                _usaStocks.value = getUSAStocks()
            }}}

    /*fun getUSAStocks(){
        println("Current working directory: " + System.getProperty("user.dir"))

        val file = File("src/main/java/com/example/stockapp/stockApi/ticks25.csv")

        val tickers = File("$file").readLines()

            tickers.forEach { ticker ->

                    val quote = apiClient.quote(ticker)
                    println("Quote for $ticker: $quote")}
    }
}*/

     private suspend fun getUSAStocks(): List<StockInfo> {
        val inputStream = assets.open("ticks25.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stockInfoList = mutableListOf<StockInfo>()
        var line: String

        while (reader.readLine().also { line = it } != null) {
            val ticker = line.trim() // Make sure to trim the line to remove any newline characters or whitespace
            val quote = apiClient.quote(ticker)

            // Create a new StockInfo object for each ticker's info and add it to stockInfoList
            val stockInfo = StockInfo(
                symbol = ticker,
                currentPrice = quote.c.toString(),
                previousClosePrice = quote.pc.toString()
            )
            stockInfoList.add(stockInfo)
        }
        reader.close()

        // Return the list of stock information
        return stockInfoList
    }
}







