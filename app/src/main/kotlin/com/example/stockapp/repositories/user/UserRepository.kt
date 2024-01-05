package com.example.stockapp.repositories.user

import android.content.Intent
import android.content.IntentSender
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

    private val _signInIntentSender = MutableStateFlow<IntentSender?>(null)
    val signInIntentSender: StateFlow<IntentSender?> = _signInIntentSender
    val authState: StateFlow<AuthenticationState> = authentication.state

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state

    companion object {
        // Unique request code
        const val REQ_ONE_TAP = 1001
    }
    init {
        CoroutineScope(Dispatchers.Default).launch {
            authentication.state.collect { authState ->
                if (authState.ready) {
                    val loggedInUser = authState.currentUser
                    Log.i("UserRepository", "User log in status: $loggedInUser")

                    if (loggedInUser != null) {
                        Log.i("UserRepository", "Collecting user data from database")
                        _state.update {  currentState ->
                            currentState.copy(
                                firstName = database.retrieve("users", listOf(authState.userId, "firstName"), ""),
                                lastName = database.retrieve("users", listOf(authState.userId, "lastName"), ""),
                                username = database.retrieve("users", listOf(authState.userId, "username"), ""),
                                email = database.retrieve("users", listOf(authState.userId, "email"), ""),
                                country = database.retrieve("users", listOf(authState.userId, "country"), ""),
                                benches = database.retrieve("users", listOf(authState.userId, "benches"), 0),
                                level = database.retrieve("users", listOf(authState.userId, "level"), 0),
                                )
                        }
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

    fun send (email: String,password: String) {
        authentication.signInUser(email,password)
    }
    fun signInGoogleRequest() {
        authentication.onSignInRequired = { intentSender ->
            _signInIntentSender.value = intentSender
        }
        authentication.displayGoogleUISignIn()
    }
    fun processSignInResult(data: Intent)
    {
        authentication.signIn(Authentication.REQ_ONE_TAP,data)
    }
    fun updateIntentSender(intentSender: IntentSender?)
    {
        _signInIntentSender.value = intentSender
    }
}