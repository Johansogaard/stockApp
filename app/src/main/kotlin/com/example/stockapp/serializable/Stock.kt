package com.example.stockapp.serializable

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val symbol: String,
    val companyName: String,
    val currency: String,
    val exchange: String,
    val lastPrice: String,
    val prevClose: String,
    val open: String,
    val high: String,
    val low: String,
    val volume: String,
    val avgVolume3m: String,
    val change: String,
    val changePercentage: String,
    val fiftyTwoWeekHigh: String,
    val fiftyTwoWeekLow: String,
    val marketTime: String,
    val history: List<Pair<String, String>>
)