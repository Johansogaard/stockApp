package com.example.stockapp.test

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.serializable.Stock
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class StockApiUnitTests {

    private lateinit var appContext: Context
    private lateinit var stockApi: StockApi

    @Before
    fun setupTestEnvironment() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        stockApi = StockApi(appContext)
    }

    @Test
    fun retrieveNVOStockWithHistoryTest() = runTest {
        val stock: Stock? = stockApi.retrieveStock("NVO", start = "2024-01-01%2000:00:00", end = "2024-02-01%2000:00:00")
        Log.i("StockApi", "Found result: \n$stock")
        TestCase.assertNotNull("Stock should not be null", stock)
    }

    @Test
    fun testConnectionToStockApi() = runTest {
        assert(stockApi.testConnection())
    }

    @Test
    fun searchForGMEStock() = runTest {
        val search: List<String>? = stockApi.retrieveSearch("GME")
        var result = false
        if (search != null) {
            for (item in search) {
                Log.i("search", "found matching result: $item")
                if (item.contains("GME")) {
                    result = true
                }
            }
        }
        assert(result)
    }

    @Test
    fun retrieveAnyListOfStocksWithSizeFive() = runTest {
        val search: List<Stock>? = stockApi.retrieveAnyListOfStocks(limit="5", offset="0")
        if (search != null) {
            for (item in search) {
                Log.i("search", "found matching result: $item")
            }
        }
        assert(search != null)
    }

    @Test
    fun retrieveSpecificListOfThreeStocks() = runTest {
        val stocks: List<String> = listOf("GME", "NVO", "AAPL")
        val search: List<Stock>? = stockApi.retrieveListOfStocks(symbols = stocks)
        if (search != null) {
            for (item in search) {
                Log.i("search", "found matching result: $item")
            }
        }
        assert(search != null)
    }

}