package com.example.stockapp.integration.api.firebase

import com.google.firebase.auth.FirebaseUser

interface AuthenticationInterface {
    //for future implementation
    fun signUp()

    fun signin()

    fun signOut()

    fun getCurrentUser(): FirebaseUser?

}