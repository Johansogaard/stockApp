package com.example.stockapp.serializable

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val email: String = "",
    val country: String = "",
    val balance: Double = 0.0,
    val stocks: Map<Int, Stock> = emptyMap(),
)
