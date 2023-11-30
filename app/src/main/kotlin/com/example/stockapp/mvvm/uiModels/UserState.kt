package com.example.stockapp.mvvm.uiModels

import com.example.stockapp.model.User

data class UserState(
    val isLoggedIn: Boolean = false,
    val user: User? = null,
    var name: String = "",
    var email: String = "",
    //var phoneNumber: String = ""
)