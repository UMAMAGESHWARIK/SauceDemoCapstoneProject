package pages;

import base.DriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

   
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.xpath("//a[text()='Logout']");
    private By aboutLink = By.id("about_sidebar_link");
    // Footer icons
    private By twitterIcon = By.cssSelector("a[href*='twitter']");
    private By facebookIcon = By.cssSelector("a[href*='facebook']");
    private By linkedInIcon = By.cssSelector("a[href*='linkedin']");
    
    
    public MenuPage() {
    	this.driver = DriverManager.getDriver();
    	if (this.driver == null) {
    		throw new IllegalStateException("Driver is not initialized. Call DriverManager.initDriver() first!");
    		}
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	}
    

    // Check if the menu button is visible on the page
    public boolean isMenuButtonVisible() {
        return driver.findElement(menuButton).isDisplayed();
    }
    
    // Click on the menu button to open the menu
    public void openMenu() {
        driver.findElement(menuButton).click();
    }

    // Perform logout by opening menu and clicking logout link
    public void logout() {
    	openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
    
    // Navigate to the 'About' page via the menu
    public void goToAbout() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink)).click();
       
    }
    
    // Check if the Twitter icon is visible
    public boolean isTwitterIconVisible() {
        return driver.findElement(twitterIcon).isDisplayed();
    }
    
    // Check if the Facebook icon is visible
    public boolean isFacebookIconVisible() {
        return driver.findElement(facebookIcon).isDisplayed();
    }
   
    // Check if the LinkedIn icon is visible
    public boolean isLinkedInIconVisible() {
        return driver.findElement(linkedInIcon).isDisplayed();
    }

   // Verify if the current page is the login page
    public boolean isAtLoginPage() {
        return driver.getCurrentUrl().contains("saucedemo.com");
    }
}

