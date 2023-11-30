package com.example.stockapp.data

data class User(
    val email: String,
    val username: String,
    val balance: Double = 0.0,
    val ownedStocks: Map<String, Int> = emptyMap(),
    val imageURL: String = ""
)