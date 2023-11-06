package com.example.stockapp.stockApi

import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import org.junit.Test
import org.junit.Assert.*



object FinnhubApi {
    private val api: DefaultApi

    init {
        ApiClient.apiKey["token"] = "key" // Replace with your actual API key
        api = DefaultApi()

    }

    fun getClient(): DefaultApi {
        return api
    }

}


