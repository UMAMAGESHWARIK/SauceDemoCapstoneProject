package testcases;

import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
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

    

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
