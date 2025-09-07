package StepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("I open the SauceDemo login page")
    public void openLoginPage() {
        // Initialize driver 
        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        // create page object
        loginPage = new LoginPage();

        // Open the page
        loginPage.open();
    }

    @When("I login with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void verifySuccessfulLogin() {
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains("inventory")) {
            throw new AssertionError("Expected inventory page, but got: " + currentUrl);
        }
        DriverManager.quitDriver();
    }

    @Then("I should see an error message containing {string}")
    public void verifyErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        if (!actualMessage.contains(expectedMessage)) {
            throw new AssertionError(
                "Expected error to contain: " + expectedMessage + " but got: " + actualMessage
            );
        }
        DriverManager.quitDriver();
    }
}

