package testcases;

import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;

public class NavigationTest {
  
    WebDriver driver;
    LoginPage loginPage;
    MenuPage menuPage;

    @BeforeClass
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
    }
    @BeforeMethod
    public void login(){
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Menu page instance
        menuPage = new MenuPage();
    }

    @Test(priority=3)
    public void navigateToAboutPage() {
        menuPage.goToAbout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"), 
                "About page did not open!");
    }
    
    @Test(priority=1)
    public void verifyMenuPresent() {
        Assert.assertTrue(menuPage.isMenuButtonVisible(), 
                "Menu button is not visible!");
    }


    @Test(priority=4)
    public void verifyFooterLinksAndIcons() {
        Assert.assertTrue(menuPage.isTwitterIconVisible(), "Twitter icon missing!");
        Assert.assertTrue(menuPage.isFacebookIconVisible(), "Facebook icon missing!");
        Assert.assertTrue(menuPage.isLinkedInIconVisible(), "LinkedIn icon missing!");
       
    }
    @Test(priority=2)
    public void logoutFromMenu() {
    	
        menuPage.logout();
        Assert.assertTrue(menuPage.isAtLoginPage(), 
                "User was not redirected to login page after logout!");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

