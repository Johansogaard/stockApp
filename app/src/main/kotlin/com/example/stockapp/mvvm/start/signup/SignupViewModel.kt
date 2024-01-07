package com.example.stockapp.mvvm.start.signup

import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val state: StateFlow<SignupUiState> = _uiState.asStateFlow()

    fun signUpWithMail()
    {

    }
    fun signUpWithGoogle()
    {

    }

}