package pages;

import base.DriverManager;

import java.time.Duration;

import java.util.List;
import java.util.ArrayList;
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
    private By continueButtonn = By.id("continue");
    private By cancelButton = By.id("cancel");
    private By summaryItems = By.className("cart_item");
    private By productName = By.cssSelector(".inventory_item_name");
    private By productPrice = By.cssSelector(".inventory_item_price");
    private By productQuantity = By.cssSelector(".cart_quantity");

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
        driver.findElement(continueButtonn).click();
    }

    // Click finish button
    public void clickFinish() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();
    }

    // Cancel checkout
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    // Get error message
    public String getErrorMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        return errorMsg.getText();
    }
    // Get summary product names
    public List<String> getSummaryProductNames() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(summaryItems));

        List<WebElement> items = driver.findElements(summaryItems);
        List<String> names = new ArrayList<>();
        for (WebElement item : items) {
            names.add(item.findElement(productName).getText());
        }
        return names;
    }
    // Get summary product prices
    public List<String> getSummaryProductPrices() {
        List<WebElement> items = driver.findElements(summaryItems);
        List<String> prices = new ArrayList<>();
        for (WebElement item : items) {
            prices.add(item.findElement(productPrice).getText());
        }
        return prices;
    }
   // Get summary product quantities
    public List<String> getSummaryProductQuantities() {
        List<WebElement> items = driver.findElements(summaryItems);
        List<String> quantities = new ArrayList<>();
        for (WebElement item : items) {
            quantities.add(item.findElement(productQuantity).getText());
        }
        return quantities;
    }
   
}

