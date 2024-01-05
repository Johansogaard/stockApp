package com.example.stockapp.serializable

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val name: String = "",
    val price: Int = 0,
)