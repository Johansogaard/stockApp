package com.example.stockapp.mvvm.search.search

import androidx.lifecycle.ViewModel
import com.example.stockapp.repositories.stock.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {



}