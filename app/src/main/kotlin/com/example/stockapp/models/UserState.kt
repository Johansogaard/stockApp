package com.example.stockapp.models

import com.example.stockapp.data.User

data class UserState(
    val isLoggedIn: Boolean = false,
    val user: User? = null,
    var name: String = "",
    var email: String = "",
    var stocks:  MutableMap<String, Int> = mutableMapOf(),
    var money: Double? = 0.00,
    //var phoneNumber: String = ""
)