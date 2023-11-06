package com.example.stockapp.stockApi

import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import org.junit.Test
import org.junit.Assert.*
import com.example.stockapp.viewModels.IndexViewModel
import org.junit.Before



class ApiTests {
    private val api: DefaultApi
    private lateinit var viewModel: IndexViewModel

    init {
        ApiClient.apiKey["token"] = "key" // Replace with your actual API key
        api = DefaultApi()

    }
    @Before
    fun setUp() {
        viewModel = IndexViewModel()
    }
    /*@Test
    fun getUSAStockstest(){
        viewModel.fetchUSAStocks()
        println(viewModel.usaStocks.value)
    }*/

}


