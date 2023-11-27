package com.example.stockapp.stockApi

import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import org.junit.Test
import org.junit.Assert.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class ApiTest {
    fun fetchStockData() {
        // This needs to be run on a background thread
        Thread {
            try {
                val url = URL("http://localhost:8080/stock") // Use 10.0.2.2 for localhost if testing on an emulator
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"

                val inputStream = BufferedInputStream(httpURLConnection.inputStream)
                val response = inputStream.bufferedReader().use(BufferedReader::readText)

                // Handle the response here
                println("Response: $response")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}

fun main() {
    val apiTest = ApiTest()
    apiTest.fetchStockData()
}
