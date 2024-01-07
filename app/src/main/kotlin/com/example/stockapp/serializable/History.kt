package com.example.stockapp.serializable

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val stock_symbol: String = "SYM",
    val record_date: String = "Fri, 01 Jan 2024 00:00:00 GMT",
    val current_price: Double = 0.0,
)
