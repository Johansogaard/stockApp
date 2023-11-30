package com.example.stockapp.mvvm.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stockapp.integration.api.firebase.EmailAuthManager
import com.example.stockapp.mvvm.uiModels.UserState

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
                Log.d("Login Status", "Login Success")
            }
            else{
                println("Login failed. Error message: $errorMessage")
                _state.value = _state.value.copy(isLoggedIn = false)
                Log.d("Login Status", "Login Failed")
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
