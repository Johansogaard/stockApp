package com.example.stockapp.screens

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
import com.example.stockapp.ui.theme.TopBarGoBack
import com.example.stockapp.ui.theme.robotoFontFamily


@Composable
fun StockViewScreen(navController: NavController) {

    val steps = 5
    val pointsData = listOf(
        Point(0f, 40f),
        Point(1f, 90f),
        Point(2f, 0f),
        Point(3f, 60f),
        Point(4f, 10f),
    )

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
                Text(text = "OMX C25", fontFamily = robotoFontFamily,)
                Text(text = "Index NASDAQ: OMX C25", fontFamily = robotoFontFamily, fontWeight = FontWeight.Normal)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Column() {
                Row() {
                    Text(
                        text = "USD 1,789.00",
                        modifier = Modifier.padding(end = 20.dp), fontFamily = robotoFontFamily, fontWeight = FontWeight.Normal
                    )
                    Text(text = "-15,03 (0,91%) today", fontFamily = robotoFontFamily, fontWeight = FontWeight.Normal)
                }
                Text(
                    text = "01.57.41 PM",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = robotoFontFamily,
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
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "HIGH",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "LOW",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Column(modifier = Modifier.padding(end = 36.dp)) {
                        Text(
                            text = "X",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "X",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "X",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Column(modifier = Modifier.padding(end = 24.dp)) {
                        Text(
                            text = "Prev. close",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "High in 52W",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "Low in 52W",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Column() {
                        Text(
                            text = "X",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "X",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "X",
                            fontSize = 14.sp,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
fun StockViewScreenPreview() {
    StockViewScreen(navController = rememberNavController())
}