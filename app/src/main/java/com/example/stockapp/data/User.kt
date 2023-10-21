package com.example.stockapp.data

data class User (   val email: String = "",
                    val username: String = "",
                    val stocks: List<String> = mutableListOf())