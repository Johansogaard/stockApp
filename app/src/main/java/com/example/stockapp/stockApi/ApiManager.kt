package com.example.stockapp.stockApi

import com.google.gson.Gson
import com.google.gson.JsonElement
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import io.finnhub.api.models.Quote
import org.junit.Test
import org.junit.Assert.*

object ApiManager {

    init {
        ApiClient.apiKey["token"] = "ckvnsohr01qq199j3h9gckvnsohr01qq199j3ha0" // Replace with your actual API key
    }

    private val apiClient = DefaultApi()
    val gson = Gson()

    fun getPopularStocks(): MutableList<Stock> {
        val popularStocks = listOf("AAPL", "MSFT", "GOOGL", "AMZN", "TSLA", "NFLX")
        val stocks = mutableListOf<Stock>()
        for(i in popularStocks.indices)
        {

            val newQuote =apiClient.quote(popularStocks[i])
            val newStock = Stock(popularStocks[i],newQuote.o,newQuote.h,newQuote.l,newQuote.c,newQuote.pc,newQuote.d,newQuote.dp)
            stocks.add(newStock)

        }
       return stocks


    }
    @Test
    fun getPopularStocksTest()
    {
        ApiManager
        getPopularStocks()
    }
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
    @Test
    fun quote() {
        println(apiClient.quote("AAPL"))
    }
}


