package com.example.stockapp.integration.stockapi

import android.content.Context
import com.example.stockapp.serializable.Stock
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import android.util.Log
import com.google.common.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StockApi @Inject constructor(
    @ApplicationContext private val context: Context
){

    suspend fun testConnection(): Boolean = withContext(Dispatchers.IO) {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val urlString = "http://135.181.106.80:5000/"
        val request = Request.Builder()
            .url(urlString)
            .get()
            .header("Connection", "close")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    Log.i("StockApi", "Call to stock was successful with response code: ${response.code}")
                    return@withContext true
                }
            }
        } catch (e: Exception) {
            Log.e("StockApi", "Error in making the request: ${e.message}")
            return@withContext false
        }
        return@withContext false
    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun retrieveStock(symbol: String, start: String = "", end: String = ""): Stock? = withContext(Dispatchers.IO) {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val urlString = "http://135.181.106.80:5000/get-stock/$symbol?start_date=$start&end_date=$end"
        val request = Request.Builder()
            .url(urlString)
            .get()
            .header("Connection", "close")
            .build()

        var tries = 10
        while (tries > 0) {
            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        Log.e("StockApi", "Call to stock failed with error: ${response.code}")
                    }

                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        return@withContext Gson().fromJson(responseBody, Stock::class.java)
                    } else {
                        Log.e("StockApi", "Response body was null")
                        return@withContext null
                    }
                }
            } catch (e: Exception) {
                Log.e("StockApi", "Error in making the request: ${e.message}")
                // Wait a bit before retrying to give transient issues time to resolve
                Thread.sleep(1000)
            }
            tries -= 1
            Log.i("StockApi", "Retrying due to failure. Tries left: $tries")
        }
        Log.e("StockApi", "Failed to retrieve stock after multiple attempts")
        return@withContext null
    }

    suspend fun retrieveAnyListOfStocks(limit: String, offset: String, start: String = "", end: String = ""): List<Stock>? = withContext(Dispatchers.IO) {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val urlString = "http://135.181.106.80:5000/get-stocks-amount/?start_date=$start&end_date=$end&limit=$limit&offset=$offset"
        val request = Request.Builder()
            .url(urlString)
            .get()
            .header("Connection", "close")
            .build()

        var tries = 10
        while (tries > 0) {
            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        Log.e("StockApi", "Call to stock failed with error: ${response.code}")
                    }

                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        val searchResultsType = object : TypeToken<List<Stock>>() {}.type
                        return@withContext Gson().fromJson<List<Stock>?>(
                            responseBody,
                            searchResultsType
                        )
                    } else {
                        Log.e("StockApi", "Response body was null")
                        return@withContext null
                    }
                }
            } catch (e: Exception) {
                Log.e("StockApi", "Error in making the request: ${e.message}")
                // Wait a bit before retrying to give transient issues time to resolve
                Thread.sleep(1000)
            }
            tries -= 1
            Log.i("StockApi", "Retrying due to failure. Tries left: $tries")
        }
        Log.e("StockApi", "Failed to retrieve stock after multiple attempts")
        return@withContext null
    }

    suspend fun retrieveListOfStocks(symbols: List<String>, start: String = "", end: String = ""): List<Stock>? = withContext(Dispatchers.IO) {
        val symbolsString: String = symbols.joinToString("-")
        Log.i("StockApi", "$symbolsString")
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val urlString = "http://135.181.106.80:5000/get-stocks/$symbolsString?start_date=$start&end_date=$end"
        val request = Request.Builder()
            .url(urlString)
            .get()
            .header("Connection", "close")
            .build()

        var tries = 10
        while (tries > 0) {
            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        Log.e("StockApi", "Call to stock failed with error: ${response.code}")
                    }

                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        val searchResultsType = object : TypeToken<List<Stock>>() {}.type
                        return@withContext Gson().fromJson<List<Stock>?>(
                            responseBody,
                            searchResultsType
                        )
                    } else {
                        Log.e("StockApi", "Response body was null")
                        return@withContext null
                    }
                }
            } catch (e: Exception) {
                Log.e("StockApi", "Error in making the request: ${e.message}")
                // Wait a bit before retrying to give transient issues time to resolve
                Thread.sleep(1000)
            }
            tries -= 1
            Log.i("StockApi", "Retrying due to failure. Tries left: $tries")
        }
        Log.e("StockApi", "Failed to retrieve stock after multiple attempts")
        return@withContext null
    }

    suspend fun retrieveSearch(symbol: String, limit: Int = 1): List<String>? = withContext(Dispatchers.IO) {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val urlString = "http://135.181.106.80:5000/search/$symbol?limit=$limit"
        val request = Request.Builder()
            .url(urlString)
            .get()
            .header("Connection", "close")
            .build()

        repeat(10) { attempt ->
            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        println("Attempt $attempt - HTTP Error Code: ${response.code}")
                        return@repeat  // Continue to the next attempt
                    }

                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        val searchResultsType = object : TypeToken<List<List<String>>>() {}.type
                        val searchResults: List<List<String>> = Gson().fromJson(responseBody, searchResultsType)

                        return@withContext searchResults.mapNotNull { pair ->
                            if (pair.size == 2) "${pair[0]} - ${pair[1]}" else null
                        }
                    }
                }
            } catch (e: Exception) {
                println("Attempt $attempt - Error in making the request: ${e.message}")
            }
            // Wait a bit before retrying to give transient issues time to resolve
            if (attempt < 2) { // Don't wait after the last attempt
                Thread.sleep(1000)
            }
        }
        println("Failed to retrieve search results after multiple attempts")
        return@withContext null
    }

}