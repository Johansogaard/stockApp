package com.example.stockapp

import org.junit.Test

class loginTest {
    val Email = "Admin@mail.com"
    val Password = "Admin1"

    val loginViewModel = LoginViewModel()

    @Test
    fun attemptLogin() {
        EmailAuthManager.signIn(Email, Password) { isSuccess, errorMessage ->
            if (isSuccess && loginViewModel.state.value.isLoggedIn) {
                // Your logic when login is successful
                if (loginViewModel.state.value.isLoggedIn)
                {

                }
            } else {
                // Your logic when login fails
            }
        }
    }
}
