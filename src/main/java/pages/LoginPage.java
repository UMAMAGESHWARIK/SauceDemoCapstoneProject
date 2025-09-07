package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.DriverManager;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("h3[data-test='error']");

    // Constructor
    public LoginPage() {
        this.driver = DriverManager.getDriver(); 
    }

    // Open login page
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    // Login
    public void login(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
    
    

    // Get error text
    public String getErrorMessage() {
        return driver.findElement(error).getText();
    }
    
}

