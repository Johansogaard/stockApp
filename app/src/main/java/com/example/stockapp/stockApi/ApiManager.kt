package com.example.stockapp.stockApi

import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import org.junit.Test
import org.junit.Assert.*

class FinnhubApiTest {

    init {
        ApiClient.apiKey["token"] = "key" // Replace with your actual API key
    }

    private val apiClient = DefaultApi()

    @Test
    fun technicalIndicator() {
        val result = apiClient.technicalIndicator(
            symbol = "AAPL",
            resolution = "D",
            from = 1583098857L,
            to = 1584308457L,
            indicator = "sma",
            indicatorFields = mapOf("timeperiod" to 3)
        )
        println(result)
    }

    @Test
    fun stockCandles() {
        val result = apiClient.stockCandles("AAPL", "D", 1590988249, 1591852249)
        println(result)
    }
}


