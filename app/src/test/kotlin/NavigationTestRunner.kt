import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions

import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/"], // Update with your actual package name
  //glue = ["com.example.stockapp"], // Update with your actual package name
    plugin = ["pretty", "html:target/cucumber-report.html"]
)
class NavigationTestRunner