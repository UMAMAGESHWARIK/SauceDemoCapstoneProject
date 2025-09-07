package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;

    // Locators
    private By productTitle = By.className("title");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.xpath("//button[contains(text(),'Add to cart')]");
    private By removeButtons = By.xpath("//button[contains(text(),'Remove')]");
    private By cartIcon = By.className("shopping_cart_link");
    private By productItems = By.className("inventory_item");

    // Constructor
    public ProductsPage() {
        this.driver = DriverManager.getDriver();
    }

    // Page Title
    public String getPageTitle() {
        return driver.findElement(productTitle).getText();
    }

    // Product names
    public List<String> getAllProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }

    // Product prices
    public List<String> getAllProductPrices() {
        List<WebElement> elements = driver.findElements(productPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement e : elements) {
            prices.add(e.getText());
        }
        return prices;
    }

    
    // Add first product to cart
    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    // Remove first product
    public void removeFirstProductFromCart() {
        driver.findElements(removeButtons).get(0).click();
    }
    
    public String getFirstProductButtonText() {
        return driver.findElements(addToCartButtons).get(0).getText();
    }

    // Add multiple products
    public void addMultipleProducts(int count) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (int i = 0; i < count && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }
    
    // Click on the first product from the product list to open its details page
    public void openFirstProductDetails() {
        driver.findElements(productNames).get(0).click();
    }

    // Navigate to cart
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
    
    // Get the current page URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    // Measure the time taken for the product items to load on the page
    public long measurePageLoadTime() {
        long startTime = System.currentTimeMillis();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItems));

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
}

