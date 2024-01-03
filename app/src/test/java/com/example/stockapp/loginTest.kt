package com.example.stockapp

import android.widget.Toast
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.mvvm.screens.Screen
import com.example.stockapp.mvvm.viewModels.UserViewModel
import org.junit.Test

class loginTest {
    val Email = "Admin@mail.com"
    val Password = "Admin1"

    val userViewModel = UserViewModel()

    @Test
    fun attemptLogin() {
        EmailAuthManager.signIn(Email, Password) { isSuccess, errorMessage ->
            if (isSuccess && userViewModel.state.value.isLoggedIn) {
                // Your logic when login is successful
                if (userViewModel.state.value.isLoggedIn)
                {

                }
            } else {
                // Your logic when login fails
            }
        }
    }
}
