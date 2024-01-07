package com.example.stockapp.mvvm.stock

import co.yml.charts.common.model.Point

data class StockData(
    var steps: Int = 0,
    var pointsData: List<Point> = emptyList()
)
