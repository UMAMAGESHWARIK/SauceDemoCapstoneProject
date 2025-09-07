package testcases;

import base.DriverManager;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productPage = new ProductsPage();
    }

    @Test
    public void verifyProductListingPage() {
        Assert.assertEquals(productPage.getPageTitle(), "Products", "Product listing page not opened!");
    }

    @Test
    public void verifyProductNamesAndPricesDisplayed() {
        List<String> names = productPage.getAllProductNames();
        List<String> prices = productPage.getAllProductPrices();

        Assert.assertFalse(names.isEmpty(), "No product names displayed!");
        Assert.assertTrue(prices.size() > 0, "No product prices displayed!");
    }

   

    @Test
    public void addProductToCart() {
        productPage.addFirstProductToCart();
        productPage.goToCart();
        Assert.assertTrue(productPage.getCurrentUrl().contains("cart"),
                "Not navigated to cart after adding product!");
    }

    
    @Test
    public void removeProductFromCart() {
        // Add first product to cart
        productPage.addFirstProductToCart();
        productPage.removeFirstProductFromCart();

        String actualButtonText = productPage.getFirstProductButtonText();
        String expectedButtonText = "Add to cart";

        Assert.assertEquals(actualButtonText, expectedButtonText, "Product was not removed from cart!");
    }


    @Test
    public void openProductDetailsPage() {
        productPage.openFirstProductDetails();
        Assert.assertTrue(productPage.getCurrentUrl().contains("inventory-item"),
                "Product details page not opened!");
    }
    
    //Performance Test
    @Test
    public void measureProductPageLoadTime() {
        long loadTime = productPage.measurePageLoadTime();
        System.out.println("Products page load time: " + loadTime + " ms");
        Assert.assertTrue(loadTime < 4000, "Products page took too long to load! (>4 sec)");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
    
}
