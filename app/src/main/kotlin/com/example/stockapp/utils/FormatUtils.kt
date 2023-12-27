package com.example.stockapp.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object formatNumberUtility{
    fun formatNumber(value: Double): String {
        val symbols = DecimalFormatSymbols(Locale.US)
        symbols.groupingSeparator = '.'
        symbols.decimalSeparator = ','
        val format =  DecimalFormat("#,##0.00", symbols)
        return format.format(value)
    }}