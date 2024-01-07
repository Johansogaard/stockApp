package com.example.stockapp.mvvm.start.signup

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapp.repositories.user.UserRepository
import com.example.stockapp.serializable.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val state: StateFlow<SignupUiState> = _uiState.asStateFlow()

    val signInIntentSender:StateFlow<IntentSender?> = userRepository.signInIntentSender
    fun signUpUser(email: String, firstName: String, lastName: String, pWord: String, uName: String)
    {

        if(checkIfSignedIn()&& notNullCheck(email,firstName,lastName,uName)) {
            _uiState.value.user = createNewUserObject(email, firstName, lastName, uName)
            viewModelScope.launch {
                userRepository.initializeUser(_uiState.value.user)
            }
        }
        else if(notNullCheck(email,firstName,lastName,uName,pWord))
        {
            _uiState.value.user = createNewUserObject(email, firstName, lastName, uName)
            viewModelScope.launch {
                val data = async {
                    userRepository.send(email,pWord)
                }

                val result = data.await()
                userRepository.initializeUser(_uiState.value.user)
            }
         }

    }
    fun notNullCheck(vararg strings: String?) : Boolean
    {
        return strings.all { it != null && it.length !=null }
    }

    fun checkIfSignedIn() : Boolean
    {
        return userRepository.checkSignedIn()
    }
    fun createNewUserObject(email: String,firstName: String,lastName: String,uName: String): User
    {
        val newUser = User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            username = uName

        )
       return newUser
    }
    fun processSignInResult(data: Intent)
    {

        userRepository.processSignInResult(data)
    }
    fun signInGoogleRequest()
    {
        Log.d("SignInGoogleRequest", "Sign-in has been pressed. \nIntent sender check = "+signInIntentSender.value)

        // userRepository.updateIntentSender(signInIntentSender.value)
        userRepository.signInGoogleRequest()
    }

}