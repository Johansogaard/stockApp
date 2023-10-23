package com.example.stockapp.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.data.Screen
import com.example.stockapp.models.UserState

class UserViewModel : ViewModel() {
    private val _state = mutableStateOf(UserState())

    private val authManager = EmailAuthManager
    private val currentUser = authManager.getUser()

    init {
        if  (currentUser != null)
        {
            setUserState()
            _state.value = _state.value.copy(isLoggedIn = true)
        }
        else
        {
            _state.value = _state.value.copy(isLoggedIn = false)
        }
    }

    /* State held by this ViewModel
     Note:  The View-Model holds its OWN state.
            This ensures encapsulation, and allows us to
            expose only the state we want to expose to the view.
     */
    val state: State<UserState> get() = _state

    fun login(email: String, password: String) {
        authManager.signIn(email, password)
        {
                isSuccess, errorMessage ->
            if (isSuccess)
            {
                _state.value = _state.value.copy(isLoggedIn = true)
            }
            else{
                println("Login failed. Error message: $errorMessage")
            }
        }
    }

    fun logout() {
        // Here we should have logout logic
        _state.value = _state.value.copy(isLoggedIn = false)
    }

    fun setUserState() {
        _state.value.name = EmailAuthManager.getUser()?.displayName.toString()
        _state.value.email = EmailAuthManager.getUser()?.email.toString()
        //_state.value.phoneNumber = EmailAuthManager.getUser()?.phoneNumber.toString()
    }

}
