package com.example.stockapp.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.data.DatabaseManager
import com.example.stockapp.data.User
import com.example.stockapp.models.UserState

class UserViewModel : ViewModel() {
    private val _state = mutableStateOf(UserState())

    private val authManager = EmailAuthManager

    init {
        val currentUser = authManager.getUser()
        if (currentUser != null) {
            // Fetch user data from the database
            fetchUserData(currentUser.uid) // Assuming 'uid' is a user identifier
        } else {
            _state.value = _state.value.copy(isLoggedIn = false)
        }
    }

    val state: State<UserState> get() = _state

    private fun fetchUserData(userId: String) {
        DatabaseManager.getUser(userId) { user ->
            if (user != null) {
                setUserState(user, userId)
                _state.value = _state.value.copy(isLoggedIn = true)
            } else {
                _state.value = _state.value.copy(isLoggedIn = false)
            }
        }
    }

    private fun setUserState(user: User, userId: String) {
        _state.value = _state.value.copy(
            userid=userId,
            name = user.username ?: "",
            email = user.email ?: "",
            stocks = user.stocks,
            money = user.money
        )
    }

}
