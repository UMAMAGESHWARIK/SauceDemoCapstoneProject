package testcases;

import base.BaseTest;
import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;

import java.util.List;

public class CartTest extends BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        // Login 
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Add one product and open cart
        productPage = new ProductsPage();
        productPage.addFirstProductToCart();
        productPage.goToCart();

        cartPage = new CartPage();
    }
    

    @Test
    public void verifyCartItems() {
        List<String> items = cartPage.getCartItems();
        Assert.assertTrue(items.size() > 0, "Cart is empty after adding product!");
    }

    @Test
    public void removeItemFromCart() {
        cartPage.removeFirstItem();
        List<String> items = cartPage.getCartItems();
        Assert.assertFalse(items.size() > 0, "Item was not removed from cart!");
    }

    @Test
    public void continueShoppingFromCart() {
        cartPage.continueShopping();
        Assert.assertTrue(productPage.getCurrentUrl().contains("inventory"),
                "Did not navigate back to product page!");
    }

    @Test
    public void proceedToCheckoutFromCart() {
        cartPage.proceedToCheckout();
        Assert.assertTrue(cartPage.getCurrentUrl().contains("checkout"),
                "Checkout page not opened!");
    }

    @Test
    public void checkoutWithEmptyCart() {
        productPage.goToCart();
        cartPage.proceedToCheckout();
        // Verify still on checkout page but no items
        Assert.assertTrue(cartPage.getCurrentUrl().contains("checkout"),
                "User should not proceed with empty cart!");
        Assert.assertNotNull(cartPage.getCartItems(), "Cart items list should be null");
       
    }

  
     @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
