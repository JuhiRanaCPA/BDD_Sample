package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/Groups.feature",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-report", "json:target/cucumber-report/cucumber.json", "junit:target/cucumber-report/Cucumber.xml"}
)
public class TestRunner {

}
