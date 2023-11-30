package com.example.stockapp.stockApi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import com.example.stockapp.ui.theme.Stock
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test
import org.junit.Assert.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class ApiTest {
    fun fetchStockData() {
        // test del
        Thread {
            try {
                val url = URL("http://localhost:8080/stock") // Use 10.0.2.2 for localhost if testing on an emulator
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"

                val inputStream = BufferedInputStream(httpURLConnection.inputStream)
                val response = inputStream.bufferedReader().use(BufferedReader::readText)

                println("Response: $response")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}

suspend fun getStockData(ticker: String, interval: String, count: Int): List<Triple<Float, Float, Float>> {
    // Replace with your actual API call logic
    val url = URL("http://10.0.2.2:8080/stock/$ticker/$interval/$count")
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"

    val inputStream = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
    val gson = Gson()
    val listType = object : TypeToken<List<Triple<Double, Double, Double>>>() {}.type
    val result: List<Triple<Double, Double, Double>> = gson.fromJson(inputStream, listType)

    // Convert the result to Float
    return result.map { Triple(it.first.toFloat(), it.second.toFloat(), it.third.toFloat()) }
}

fun findMinValue(data: List<Triple<Float, Float, Float>>): Float {
    return data.minOfOrNull { triple ->
        minOf(triple.first, triple.second, triple.third)
    } ?: 0f
}

fun findMaxValue(data: List<Triple<Float, Float, Float>>): Float {
    return data.maxOfOrNull { triple ->
        maxOf(triple.first, triple.second, triple.third)
    } ?: 0f
}

fun getytd(stockData: List<Triple<Float, Float, Float>>, yearStockData: List<Triple<Float, Float, Float>>): Float {
    if (stockData.isEmpty() || yearStockData.isEmpty()) return 0f
    val currentValue = getcurrentvalue(stockData)
    val previousValue = yearStockData.lastOrNull()?.first ?: 0f
    return ((currentValue/previousValue)*100)-100
}

fun getcurrentvalue(stockData:  List<Triple<Float, Float, Float>>): Float {
    if (stockData.isEmpty()) return 0f
    return stockData.lastOrNull()?.first ?: 0f
}
@Composable
fun ShowStockists(
    navController: NavController,
    stockNames: List<String>
) {
    val coroutineScope = rememberCoroutineScope()
    var stockPrices by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var stockStarts by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var stockPictures by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

    LaunchedEffect(key1 = stockNames) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val nationalities = getNationalities(stockNames)

                val tempStockPrices = mutableMapOf<String, String>()
                val tempStockStarts = mutableMapOf<String, String>()
                val tempStockPictures = mutableMapOf<String, String>()

                stockNames.forEach { stockName ->
                    val priceString = getStockData(stockName, "MINUTE", 1)
                    val startString = getStockData(stockName, "DAY", 52)
                    val change = getytd(priceString, startString)

                    tempStockPrices[stockName] = "%.2f".format(getcurrentvalue(priceString))
                    tempStockStarts[stockName] = "%.2f".format(change)
                    tempStockPictures[stockName] = getAdjustedPictureName(nationalities[stockName] ?: "mikkel.jpg")
                }

                withContext(Dispatchers.Main) {
                    stockPrices = tempStockPrices
                    stockStarts = tempStockStarts
                    stockPictures = tempStockPictures
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        stockNames.forEach { stockName ->
            val price = stockPrices[stockName] ?: "Fetching..."
            val change = stockStarts[stockName] ?: "Fetching..."
            val picture = stockPictures[stockName] ?: "mikkel.jpg"

            Stock(
                picture = picture,
                text = stockName,
                price = price,
                perftdy = change,
                navController
            )
        }
    }
}

fun getAdjustedPictureName(picture: String): String {
    return when (picture) {
        "C25" -> "dk.png"
       "S&P500" -> "usa.png"
        else -> "mikkel.jpg"
    }
}

suspend fun getNationalities(tickers: List<String>): Map<String, String> {
    val gson = Gson()
    val url = URL("http://10.0.2.2:8080/stock/nationalities/${tickers.joinToString(",")}")
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"

    try {
        val inputStream = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
        val typeToken = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(inputStream, typeToken)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        httpURLConnection.disconnect()
    }

    return emptyMap()
}

suspend fun getGroupTickers(groupName: String): List<String> {
    val url = URL("http://10.0.2.2:8080/group/tickers/$groupName")
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"

    val inputStream = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
    val gson = Gson()
    val listType = object : TypeToken<List<String>>() {}.type
    return gson.fromJson(inputStream, listType)
}

fun main() {
    val apiTest = ApiTest()
    apiTest.fetchStockData()
}
