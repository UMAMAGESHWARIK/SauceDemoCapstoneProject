package testcases;

import base.DriverManager;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productPage;

    @BeforeClass
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
    }
    @BeforeMethod
    public void login() {
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
        productPage.addProductToCart();
        productPage.goToCart();
        Assert.assertTrue(productPage.getCurrentUrl().contains("cart"),
                "Not navigated to cart after adding product!");
    }

    
    @Test
    public void removeProductFromCart() {
        // Add first product to cart
        productPage.addProductToCart();
        productPage.removeProductFromCart();

        String actualButtonText = productPage.getProductButtonText();
        String expectedButtonText = "Add to cart";

        Assert.assertEquals(actualButtonText, expectedButtonText, "Product was not removed from cart!");
    }


    @Test
    public void openProductDetailsPage() {
        productPage.openProductDetails();
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

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
    
}
