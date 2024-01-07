package com.example.stockapp.repositories.user

import android.util.Log
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.integration.firebase.authentication.AuthenticationState
import com.example.stockapp.integration.firebase.database.FirebaseDatabaseConnection
import com.example.stockapp.serializable.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val authentication: Authentication,
    private val database: FirebaseDatabaseConnection
) {

    private val authState: StateFlow<AuthenticationState> = authentication.state

    private val _state = MutableStateFlow(User())
    val state: StateFlow<User> = _state

    init {
        CoroutineScope(Dispatchers.Default).launch {
            authentication.state.collect { authState ->
                if (authState.ready) {
                    val loggedInUser = authState.currentUser
                    Log.i("UserRepository", "User log in status: $loggedInUser")

                    if (loggedInUser != null) {
                        Log.i("UserRepository", "Collecting user data from database")
                        _state.value = database.retrieve(path = "users", refPath = listOf(authState.userId, "portfolio", "stocks"), defaultValue =  User())
                        return@collect
                    } else {
                        Log.i("UserRepository", "Unable to find user in database")
                        return@collect
                    }
                }
            }
        }
    }

    suspend fun initializeUser(
        newUser: User
        ) {
        Log.i("userRepository", "Creating new user...")

        coroutineScope {
            database.update("users", listOf(authState.value.userId), newUser)
            authentication.checkUserCreated { }
        }
    }

    fun clearCache() {
        database.clearCache()
    }

    fun signOut() {
        authentication.signOut()
    }


}