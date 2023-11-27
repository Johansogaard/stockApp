import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.stockapp.MainActivity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.After


class NavigationSteps {
    private val robot = MainActivity()
    private val activityRule = ActivityTestRule(MainActivity::class.java, false, false)
    var activity: MainActivity = Robolectric.setupActivity(MyActivity::class.java)
    @Given("^I start the application$")
    fun i_start_app() {
        robot.launchLoginScreen(activityRule)
    }
    private var activityScenario: ActivityScenario<MainActivity>? = null
    @Given("the app is open")
    fun theAppIsOpen() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @When("I navigate to the {string}")
    fun iNavigateTo(screen: String) {
        // Your code to navigate to the specified screen
        // You might use Espresso, Navigation Component, or other navigation mechanisms
        onView(withText("Get Started")).perform(click())
        onView(withText("Login")).perform(click())
    }

    @Then("I should see the {string}")
    fun iShouldSee(screen: String) {
        // Your code to validate that the current screen matches the expected screen
        // For demonstration purposes, let's assume you have a TextView with the screen name
        onView(withText(screen)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        // Close the activityScenario to clean up resources
        activityScenario?.close()
    }
}