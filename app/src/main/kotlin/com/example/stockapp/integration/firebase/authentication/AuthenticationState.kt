package com.example.stockapp.integration.firebase.authentication

import com.google.firebase.auth.FirebaseUser

data class AuthenticationState(
    val currentUser: FirebaseUser? = null,
    val userCreated: Boolean = false,
    val loggedIn: Boolean = false,
    val userId: String = "",
    val coins: Int = 0,
    val ready: Boolean = false,
)
