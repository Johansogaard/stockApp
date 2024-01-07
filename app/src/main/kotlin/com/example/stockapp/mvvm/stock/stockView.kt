package com.example.stockapp.mvvm.stock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
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
import com.example.stockapp.ui.theme.TopBarGoBack

@Composable
fun StockViewScreen(navController: NavController) {

    val stockViewModel: StockViewModel = hiltViewModel()

    val uiState by stockViewModel.state.collectAsState()

    stockViewModel.addTestStock()
    stockViewModel.testCallSetStockToNVO()

    val steps = uiState.stockData.steps
    val pointsData = uiState.stockData.pointsData

    if (uiState.stockData.steps != 0) {
        val xAxisData = AxisData.Builder()
            .axisStepSize(100.dp)
            .backgroundColor(Color.Transparent)
            .steps(pointsData.size - 1)
            .labelData { i -> i.toString() }
            .labelAndAxisLinePadding(15.dp)
            .axisLineColor(MaterialTheme.colorScheme.primary)
            .axisLineColor(MaterialTheme.colorScheme.secondary)
            .build()

        val yAxisData = AxisData.Builder()
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
            linePlotData = LinePlotData(
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
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
        )

        Column {
            Column() {
                TopBarGoBack("Details", navController = navController)
            }
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column() {
                    Text(text = "Text", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = uiState.stock.exchange,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                Column() {
                    Row() {
                        Text(
                            text = uiState.stock.last_price.toString(),
                            modifier = Modifier.padding(end = 20.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "-15,03 (0,91%) today",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Text(
                        text = "01.57.41 PM",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.DarkGray
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                LineChart(
                    modifier = Modifier
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
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun StockViewScreenPreview() {
    StockViewScreen(
        navController = rememberNavController()
    )
}