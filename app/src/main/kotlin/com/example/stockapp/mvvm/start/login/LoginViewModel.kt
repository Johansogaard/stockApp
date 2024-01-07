package com.example.stockapp.mvvm.start.login

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _uiState.asStateFlow()

  //  private val _signInIntentSender = MutableStateFlow<IntentSender?>(null)
   // val signInIntentSender: StateFlow<IntentSender?> = _signInIntentSender
    val signInIntentSender:StateFlow<IntentSender?> = userRepository.signInIntentSender
    fun signInMailRequest(email: String , password: String)
    {
        _uiState.value.pword = password
        _uiState.value.mail =email

    userRepository.send(_uiState.value.mail!!,_uiState.value.pword!!)
    }
    fun signInGoogleRequest()
    {
        Log.d("SignInGoogleRequest", "Sign-in has been pressed. \nIntent sender check = "+signInIntentSender.value)

       // userRepository.updateIntentSender(signInIntentSender.value)
        userRepository.signInGoogleRequest()
    }
    fun send ()
    {

    }
    fun processSignInResult(data: Intent)
    {

       userRepository.processSignInResult(data)
    }

}
