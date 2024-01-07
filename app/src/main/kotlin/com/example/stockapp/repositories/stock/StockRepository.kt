package com.example.stockapp.repositories.stock

import android.content.Context
import com.example.stockapp.integration.stockapi.StockApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stockApi: StockApi,
){


}