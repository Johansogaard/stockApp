package com.example.stockapp.data

import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object DatabaseManager {
    val databaseUrl = "https://stockapp-26f58-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(databaseUrl)
    val myRef = database.getReference("users")

    fun addUser(email: String, username: String, userId: String) {
        val user = User( email, username, 10000.00, mutableMapOf("AAPL" to 10)) // Assuming default values
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


    fun updateStocks(userId: String, stockName: String, quantity: Int) {
        myRef.child(userId).child("stocks").child(stockName).setValue(quantity)
    }
    fun getStocks(userId: String, callback: (Map<String, Int>?) -> Unit) {
        myRef.child(userId).child("stocks").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val stocks = task.result?.getValue(object : GenericTypeIndicator<Map<String, Int>>() {})
                callback(stocks)
            } else {
                callback(null)
            }
        }
    }

    fun updateMoney(userId: String, amount: Double) {
        myRef.child(userId).child("money").setValue(amount)
    }


    fun getMoney(userId: String, callback: (Double?) -> Unit) {
        myRef.child(userId).child("money").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val money = task.result?.getValue(Double::class.java)
                callback(money)
            } else {
                callback(null)
            }
        }
    }


}

