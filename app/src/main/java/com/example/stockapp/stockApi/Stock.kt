package com.example.stockapp.stockApi

data class Stock(
    val name: String,
    val o: Float?,
    val h: Float?,
    val l: Float?,
    val c: Float?,
    val pc: Float?,
    val d: Float?,
    val dp: Float?
)