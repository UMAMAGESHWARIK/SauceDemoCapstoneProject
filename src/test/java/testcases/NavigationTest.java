package testcases;

import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;

@Listeners(utils.TestListener.class)
public class NavigationTest {

    WebDriver driver;
    LoginPage loginPage;
    MenuPage menuPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        // Login pre-condition
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Menu page instance
        menuPage = new MenuPage();
    }

    @Test
    public void verifyMenuPresent() {
        Assert.assertTrue(menuPage.isMenuButtonVisible(), 
                "Menu button is not visible!");
    }

    @Test
    public void logoutFromMenu() {
    	
        menuPage.logout();
        Assert.assertTrue(menuPage.isAtLoginPage(), 
                "User was not redirected to login page after logout!");
    }

    @Test
    public void navigateToAboutPage() {
        menuPage.goToAbout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"), 
                "About page did not open!");
    }

    @Test
    public void verifyFooterLinksAndIcons() {
        Assert.assertTrue(menuPage.isTwitterIconVisible(), "Twitter icon missing!");
        Assert.assertTrue(menuPage.isFacebookIconVisible(), "Facebook icon missing!");
        Assert.assertTrue(menuPage.isLinkedInIconVisible(), "LinkedIn icon missing!");
       
    }
    

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
