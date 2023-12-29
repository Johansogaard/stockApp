package com.example.stockapp.test

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.MainActivity
import com.example.stockapp.screens.IntroScreen
import com.example.stockapp.screens.searchStocks
import com.example.stockapp.stockApi.*
import com.example.stockapp.stockApi.getGroupTickers
import com.example.stockapp.stockApi.getStockData
import com.example.stockapp.stockApi.getcurrentvalue
import com.example.stockapp.ui.theme.StockAppTheme
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


class userStorystep2 {

//initiate at null
    var stocks = listOf<String>()
    var stockData: List<Triple<Float, Float, Float>> = listOf()
    var max: Float?=null
    var min: Float?=null
    var currentprice: Float?=null
    var ytd: Float?=null

    @Given("a stock exist")
    fun i_start_connection() {
// we check if the stock exist
        runBlocking { stocks=getGroupTickers("S&P500") }
        assertTrue(stocks.size >= 1)
// we can see that there is 1 or more S&P500 stocks exist
    }
    @When("I call for the stock information")
    fun iCall_for_data() {


        runBlocking {      stockData=getStockData("${stocks.elementAt(0)}", "DAY", 10) }
        runBlocking {      max= findMaxValue(stockData) }
        runBlocking {      min=findMinValue(stockData) }
        runBlocking {      currentprice=getcurrentvalue(stockData) }
        runBlocking {      ytd=getytd(stockData,getStockData("${stocks.elementAt(0)}", "DAY", 100) )  }

    }

    @Then("I should see stock information")
    fun iShouldSee() {

        //we test if we have gotten a information
        assertTrue(max!=null)
        assertTrue(min!=null)
        assertTrue(currentprice!=null)
        assertTrue(ytd!=null)
        // we have gotten the information this can be shown to the user
    }

  @Composable
  fun createNavController(): NavController {

    return rememberNavController();
  }


}
