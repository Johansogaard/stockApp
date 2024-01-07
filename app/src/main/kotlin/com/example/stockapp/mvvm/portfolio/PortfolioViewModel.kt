package com.example.stockapp.mvvm.portfolio

import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.stock.StockRepository
import com.example.stockapp.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val stockRepository: StockRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioUiState())
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    fun signOut() {
        userRepository.signOut()
    }

    /*private val _stockData = MutableStateFlow<Stock>()
    val stockData: StateFlow<Stock> = _stockData */

    /*fun refreshStockData() {
        val stock =
    }*/


}