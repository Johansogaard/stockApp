package com.example.stockapp.integration.firebase.authentication

import com.google.firebase.auth.FirebaseUser

interface AuthenticationInterface {
    //for future implementation
    fun signUp()

    fun signin()

    fun signOut()

    fun getCurrentUser(): FirebaseUser?

}