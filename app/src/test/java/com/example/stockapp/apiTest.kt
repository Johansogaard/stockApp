package com.example.stockapp

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class apiTest {
    val baseURL = "https://smiling-quietly-thrush.ngrok-free.app"

    @Test
    fun testGetHistoricalData() {
        val ticker = "AAPL"
        val interval = "MINUTE"
        val count = 150

        val url = URL(baseURL+"/stock/$ticker/$interval/$count")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        val inputStream = BufferedInputStream(httpURLConnection.inputStream)
        val response = inputStream.bufferedReader().use(BufferedReader::readText)

        val gson = Gson()
        val listType = object : TypeToken<List<Triple<Double, Double, Double>>>() {}.type
        val result: List<Triple<Double, Double, Double>> = gson.fromJson(response, listType)

        println("Response as List<Triple<Double, Double, Double>>: $result")
        assertEquals(count, result.size)
        result.forEach { triple ->
            // Assert that each Triple contains non-zero values
            assertTrue(triple.first != 0.0)
            assertTrue(triple.second != 0.0)
            assertTrue(triple.third != 0.0)
        }
    }

    @Test
    fun testGetHistoricalData2() {
        val ticker = "NOVO"
        val interval = "FIFTEEN_MINUTES"
        val count = 20

        val url = URL(baseURL+"/stock/$ticker/$interval/$count")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        val inputStream = BufferedInputStream(httpURLConnection.inputStream)
        val response = inputStream.bufferedReader().use(BufferedReader::readText)

        val gson = Gson()
        val listType = object : TypeToken<List<Triple<Double, Double, Double>>>() {}.type
        val result: List<Triple<Double, Double, Double>> = gson.fromJson(response, listType)

        println("Response as List<Triple<Double, Double, Double>>: $result")
        assertEquals(count, result.size)
        result.forEach { triple ->
            // Assert that each Triple contains non-zero values
            assertTrue(triple.first != 0.0)
            assertTrue(triple.second != 0.0)
            assertTrue(triple.third != 0.0)
        }
    }

    @Test
    fun testGetHistoricalData3() {
        val ticker = "NOVO"
        val interval = "HOUR"
        val count = 5

        val url = URL(baseURL+"/stock/$ticker/$interval/$count")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        val inputStream = BufferedInputStream(httpURLConnection.inputStream)
        val response = inputStream.bufferedReader().use(BufferedReader::readText)

        val gson = Gson()
        val listType = object : TypeToken<List<Triple<Double, Double, Double>>>() {}.type
        val result: List<Triple<Double, Double, Double>> = gson.fromJson(response, listType)

        println("Response as List<Triple<Double, Double, Double>>: $result")
        assertEquals(count, result.size)
        result.forEach { triple ->
            // Assert that each Triple contains non-zero values
            assertTrue(triple.first != 0.0)
            assertTrue(triple.second != 0.0)
            assertTrue(triple.third != 0.0)
        }
    }



    @Test
    fun testGetGroupTickers() {
        val groupName = "C25" // Example ticker

        val url = URL(baseURL+"/group/tickers/$groupName")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        val inputStream = BufferedInputStream(httpURLConnection.inputStream)
        val response = inputStream.bufferedReader().use(BufferedReader::readText)

        // Parse the JSON response into a list of Doubles
        val gson = Gson()
        val listType = object : TypeToken<List<String>>() {}.type
        val strings: List<String> = gson.fromJson(response, listType)
        assertTrue(strings.isNotEmpty())
        println("Response as List<String>: $strings")
        //returns a list with strings like [NOVO, APPLE]
    }
    @Test
    fun testGetStockNationalities() {
        val tickers = URLEncoder.encode("[NOVO,AAPL]", "UTF-8")
        val url = URL(baseURL+"/stock/nationalities/$tickers")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        val inputStream = BufferedInputStream(httpURLConnection.inputStream)
        val response = inputStream.bufferedReader().use(BufferedReader::readText)

        val gson = Gson()
        val type = object : TypeToken<Map<String, String>>() {}.type
        val nationalities: Map<String, String> = gson.fromJson(response, type)

        println("Response as Map<String, String>: $nationalities")

        assertTrue(nationalities["NOVO"]=="C25")
        assertTrue(nationalities.containsKey("AAPL"))
    }

    @Test
    fun testSearchStocks() {
        val query = "AAP" // Example search query

        val url = URL(baseURL+"/search/stocks/${URLEncoder.encode(query, "UTF-8")}")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        val inputStream = BufferedInputStream(httpURLConnection.inputStream)
        val response = inputStream.bufferedReader().use(BufferedReader::readText)

        val gson = Gson()
        val listType = object : TypeToken<List<String>>() {}.type
        val searchResults: List<String> = gson.fromJson(response, listType)

        println("Response as List<String>: $searchResults")

        // Check if the search results contain the query string
        assertTrue(searchResults.any { it.contains(query, ignoreCase = true) })
    }

}