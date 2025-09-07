package testcases;

import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(utils.TestListener.class)
public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        loginPage = new LoginPage();
    }
    

    @Test
    public void testValidLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User is not logged in!");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.open();
        loginPage.login("standard_wrong_user", "secret_wrong_sauce");

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Epic sadface"),
                "Error message not displayed!");
    }

    

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
