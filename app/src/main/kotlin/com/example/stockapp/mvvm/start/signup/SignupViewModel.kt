package com.example.stockapp.mvvm.start.signup

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

    fun signUpUser(email: String, firstName: String, lastName: String, pWord: String, uName: String)
    {
        if(userRepository.checkSignedIn()) {
            _uiState.value.user = createNewUserObject(email, firstName, lastName, uName)
            viewModelScope.launch {
                userRepository.initializeUser(_uiState.value.user)
            }
        }
        else
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


}