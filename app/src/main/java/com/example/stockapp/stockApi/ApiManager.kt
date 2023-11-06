package com.example.stockapp.stockApi

import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient


class ApiManager {
    val apiKey = "ckvnsohr01qq199j3h9gckvnsohr01qq199j3ha0" // Replace YOUR_API_KEY with your actual Finnhub API key
    val baseUrl = "https://finnhub.io/api/v1/"
    val apiClient = DefaultApi()
fun setupApiManger(){

    ApiClient.apiKey["token"] = apiKey
}



        fun getCandels(){





        println(apiClient.stockCandles("AAPL", "D", 1590988249, 1591852249))
    }
    }



