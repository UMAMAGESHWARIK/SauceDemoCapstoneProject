package pages;

import base.DriverManager;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage {

    private WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By cancelBtn = By.id("cancel");
    private By summaryItems = By.className("cart_item");

    // Constructor
    public CheckOutPage() {
        this.driver = DriverManager.getDriver();
    }

    // Enter checkout information
    public void enterCheckoutInformation(String first_name, String last_name, String zip) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(first_name);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(last_name);
        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(zip);
    }

    // Click continue button
    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    // Click finish button
    public void clickFinish() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();
    }

    // Cancel checkout
    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }

    // Get error message
    public String getErrorMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        return errorMsg.getText();
    }

    // Get number of items in order summary
    public int getSummaryItemCount() {
        return driver.findElements(summaryItems).size();
    }

    // Get current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

