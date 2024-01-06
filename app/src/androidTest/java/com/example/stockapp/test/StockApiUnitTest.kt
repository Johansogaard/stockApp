package com.example.stockapp.test

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.serializable.Stock
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest

class StockApiUnitTest {

    private lateinit var appContext: Context
    private lateinit var stockApi: StockApi

    @Before
    fun initializeContext() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Before
    fun initializeStockApi() {
        stockApi = StockApi(appContext)
    }

    @Test
    suspend fun retrieveNVOStockTest() = runTest {
        val stock: Stock? = stockApi.retrieveStock("NVO")
        assertNotNull("Stock should not be null", stock)
    }

    @Test
    suspend fun testConnectionToStockApi() {
        assert(stockApi.testConnection())
    }

    @Test
    suspend fun searchForGMEStock() {
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
}


