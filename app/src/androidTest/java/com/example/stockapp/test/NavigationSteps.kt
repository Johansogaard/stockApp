package com.example.stockapp.test

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.MainActivity
import com.example.stockapp.mvvm.start.intro.IntroScreen
import com.example.stockapp.stockApi.searchStocks
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


class NavigationSteps {
//initiate at 0
    var stock1: Float? =null
    var stock2: Float? =null

    var stocks = listOf<String>()
    @Given("The stocks from S&P500 exist")
    //we will test for only 2 stocks in this test
    fun i_start_connection() {
// we check if the stocks exist
       runBlocking { stocks=getGroupTickers("S&P500") }
        assertTrue(stocks.size >= 2)
// we can see that 2 or over S&P500 exist
    }


  @When("I call for the for the stocks from S&P500 and their stock price")
  fun iCall_for_data() {

// we give them a value
     runBlocking {      stock1=getcurrentvalue(getStockData("${stocks.elementAt(0)}", "MINUTE", 1)) }
     runBlocking {      stock2=getcurrentvalue(getStockData("${stocks.elementAt(1)}", "MINUTE", 1)) }


  }

    @Then("I should see the a list of stocks from S&P500 and their stock price")
    fun iShouldSee() {

        //we test if we have gotten a price
        assertTrue(stock1!=null)
        assertTrue(stock2!=null)
       // you get their price, this can be shown to the user
    }}


