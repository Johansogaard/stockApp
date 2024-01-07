package com.example.stockapp.mvvm.portfolio

import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.stock.StockRepository
import com.example.stockapp.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val stockRepository: StockRepository,
    private val userRepository: UserRepository
) : ViewModel() {


    fun signOutOfApp()
    {
        userRepository.signOut()
    }


}