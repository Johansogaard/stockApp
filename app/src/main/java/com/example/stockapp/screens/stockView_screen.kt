package com.example.stockapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.stockapp.R
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.theme.TopBarGoBack
import com.example.stockapp.ui.theme.robotoFontFamily
import com.example.stockapp.viewModels.StocksViewModel

@Composable
fun StockViewScreen(navController: NavController, stocksViewModel: StocksViewModel) {

    stocksViewModel.addTestStock()
    val steps = stocksViewModel.state.value.stocks[0].stockData.steps
    val pointsData = stocksViewModel.state.value.stocks[0].stockData.pointsData

    val XAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.primary)
        .axisLineColor(MaterialTheme.colorScheme.secondary)
        .build()

    val YAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(15.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.primary)
        .axisLineColor(MaterialTheme.colorScheme.secondary)
        .build()

    val lineChartData = LineChartData(
        linePlotData= LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.secondary,
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = XAxisData,
        yAxisData = YAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
    )

    Column {
        Column() {
            TopBarGoBack("Details", navController = navController)
        }
        Column(modifier = Modifier
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column() {
                Text(text = "OMX C25", style = MaterialTheme.typography.titleMedium)
                Text(text = "Index NASDAQ: OMX C25", style = MaterialTheme.typography.titleMedium)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Column() {
                Row() {
                    Text(
                        text = "USD 1,789.00",
                        modifier = Modifier.padding(end = 20.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = "-15,03 (0,91%) today", style = MaterialTheme.typography.bodyLarge)
                }
                Text(
                    text = "01.57.41 PM",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.DarkGray
                )
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            LineChart(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
                lineChartData = lineChartData
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
            Column() {
                Row() {
                    Column(modifier = Modifier.padding(end = 24.dp)) {
                        Text(
                            text = "Opening",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "HIGH",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "LOW",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.padding(end = 36.dp)) {
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.padding(end = 24.dp)) {
                        Text(
                            text = "Prev. close",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "High in 52W",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Low in 52W",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column() {
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier
                    .align(Alignment.CenterStart),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Column() {
                        Text(text = "Price", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "1,789.00", style = MaterialTheme.typography.titleMedium)
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .fillMaxWidth(),
                        onClick = { navController.navigate(Screen.BuyScreen1.route) },) {
                        Text(text = "TRADE", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
fun StockViewScreenPreview() {
    StockViewScreen(navController = rememberNavController(), stocksViewModel = StocksViewModel())
}