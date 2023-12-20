package com.example.stockapp.data

data class User (
    val email: String = "",
    val username: String = "",
    val money: Double? = 10000.00,

    val stocks: MutableMap<String, Int> = mutableMapOf()
    // settings maybe
)