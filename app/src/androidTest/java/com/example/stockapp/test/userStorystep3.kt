/*package com.example.stockapp.test

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.stockApi.*
import com.example.stockapp.stockApi.getStockData
import com.example.stockapp.stockApi.getcurrentvalue
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking


class userStorystep3 {

//initiate at null

    var stock:String? = null
    var price:Float? = null


    @Given("a stock exists")
    fun i_start_connection() {
// we check that there is one stock named APPL that exists
        runBlocking { assertTrue(searchStocks("AAPL").isNotEmpty())  }
    }


    @When("I search a stock")
    fun iCall_for_data() {

//we search fo it notice "AAP" not "AAPL
        runBlocking { stock=searchStocks("AAP").first()}


    }

    @Then("i should see it")
    fun iShouldSee() {
        assertTrue(stock=="AAPL") // we get it
        // and then we can get the price and show it
        runBlocking { price= getcurrentvalue(getStockData("AAPL","MINUTE",1))}
        assertTrue(price!=null)


    }

  @Composable
  fun createNavController(): NavController {

    return rememberNavController();
  }


}
*/
