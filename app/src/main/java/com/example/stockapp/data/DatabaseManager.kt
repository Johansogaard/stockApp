package com.example.stockapp.data

import android.net.Uri
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object DatabaseManager {
    val databaseUrl = "https://stockapp-26f58-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(databaseUrl)
    val myRef = database.getReference("users")

    fun addUser(email: String, username: String, userId: String) {
        // Create a user with a non-empty map for ownedStocks
        val user = User(
            email = email,
            username = username,
            balance = 100.0,
            ownedStocks = mapOf("AAPL" to 5, "MSFT" to 10),
        )

        myRef.child(userId).setValue(user)
    }
    fun getUser(userId: String, callback: (User?) -> Unit) {
        myRef.child(userId).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result?.getValue(User::class.java)
                callback(user)
            } else {
                // Handle the error case, e.g., callback with null or an error message
                callback(null)
            }
        }
    }


}