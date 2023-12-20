package com.example.stockapp.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object DatabaseManager {
    val databaseUrl = "https://stockapp-26f58-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(databaseUrl)
    val myRef = database.getReference("users")

    fun addUser(email: String, username: String, userId: String) {
        val user = User(email, username, 10000.00, mutableMapOf()) // Assuming default values
        myRef.child(userId).setValue(user)
    }
    fun getUser(userId: String, callback: (User?) -> Unit): Unit? {
        myRef.child(userId).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result?.getValue(User::class.java)
                callback(user)
            } else {
                // Handle the error case, e.g., callback with null or an error message
                callback(null)
            }
    }
        return null
    }
}