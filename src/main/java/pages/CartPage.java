package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;

    // Locators
    private By cartItemNames = By.className("inventory_item_name");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By checkoutBtn = By.id("checkout");

    // Constructor
    public CartPage() {
        this.driver = DriverManager.getDriver();
    }
    
    // Find all cart item elements on the page
    public List<String> getCartItems() {
        List<WebElement> items = driver.findElements(cartItemNames);
        List<String> productNames = new ArrayList<>();

        for (WebElement item : items) {
            productNames.add(item.getText());
        }

        return productNames;
    }

    // Continue shopping
    public void continueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }

    // Proceed to checkout
    public void proceedToCheckout() {
        driver.findElement(checkoutBtn).click();
    }

    // Get current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
        
    }
}
