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
import com.example.stockapp.ui.theme.StockAppTheme
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test


class NavigationSteps {


  //  @JvmField
 /* @get:Rule
  val composeTestRule = createComposeRule()*/
    //val rule = createComposeRule()

  //  private val idlingResource = SimpleIdlingResource()*/


    @Given("i start the application")
    fun i_start_app() {
      // activityRule.launchActivity(null)



    }

   @Test
  @When("I click the {string} button")
    fun iNavigateTo(btnText: String) {

    }
    @Test
    @Then("I should see the {string}")
    fun iShouldSee(screen: String) {
        // Your code to validate that the current screen matches the expected screen
        // For demonstration purposes, let's assume you have a TextView with the screen name
       // onView(withText(screen)).check(matches(isDisplayed()))

    }


  @Composable
  fun createNavController(): NavController {

    return rememberNavController();
  }


}
