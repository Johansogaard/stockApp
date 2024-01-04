package com.example.stockapp.mvvm.start.signup

import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {



}