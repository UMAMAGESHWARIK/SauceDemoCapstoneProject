package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinitions",
        plugin = {"pretty",
                  "html:target/cucumber-reports/html-report.html",
                  "json:target/cucumber-reports/report.json"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

