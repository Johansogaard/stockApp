package com.example.stockapp.mvvm.stock

import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.stock.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {



}