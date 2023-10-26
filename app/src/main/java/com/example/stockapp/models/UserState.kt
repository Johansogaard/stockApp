package com.example.stockapp.models

import com.example.stockapp.data.User

data class UserState(
    val isLoggedIn: Boolean = false,
    val user: User? = null,
    var name: String = "",
    var email: String = "",
    //var phoneNumber: String = ""
)