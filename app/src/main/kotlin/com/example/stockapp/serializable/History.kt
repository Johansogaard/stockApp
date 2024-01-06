package com.example.stockapp.serializable

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val record_date: String = "2024-01-05 18:56:02",
    val current_price: Double = 0.0,
)
