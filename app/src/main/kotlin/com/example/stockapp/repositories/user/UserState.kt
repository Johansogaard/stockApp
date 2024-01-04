package com.example.stockapp.repositories.user

data class UserState (
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val email: String = "",
    val country: String = "",
    val benches: Int = 0,
    val level: Int = 0,
)