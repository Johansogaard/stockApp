package com.example.stockapp.repositories.stock

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    @ApplicationContext private val context: Context
){

    init {

    }

}