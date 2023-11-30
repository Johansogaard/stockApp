package com.example.stockapp.mvvm.uiModels

import co.yml.charts.common.model.Point

data class StockState(
    var stocks: MutableList<Stock>
)

data class Stock(
    val name: String,
    val price: Double,
    val stockData: StockData,
)

class StockData {
    var steps: Int = 0
    var pointsData: List<Point> = emptyList()
}
