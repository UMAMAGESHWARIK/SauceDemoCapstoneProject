package testcases;
import base.DriverManager;


import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckOutTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productPage;
    CartPage cartPage;
    CheckOutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
    }

        // Login
       @BeforeMethod
        public void login() {
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Add product and go to cart
        productPage = new ProductsPage();
        productPage.addProductToCart();
        productPage.goToCart();

        cartPage = new CartPage();
        cartPage.proceedToCheckout();

        checkoutPage = new CheckOutPage();
    }
       
    @Test
    public void checkoutWithValidInformation() {
        checkoutPage.enterCheckoutInformation("Uma", "Mageshwari", "600001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"),
                "Checkout was not completed successfully!");
    }

    @Test
    public void checkoutWithMissingRequiredFields() {
        // Leaving the fields blank
        checkoutPage.enterCheckoutInformation("", "", "");
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        Assert.assertTrue(error.contains("Error"),
                "Error message not displayed for missing required fields!");
    }

    @Test
    public void verifyOrderSummaryDetails() {
        checkoutPage.enterCheckoutInformation("Uma", "Mageshwari", "600001");
        checkoutPage.clickContinue();

        List<String> productNames = checkoutPage.getSummaryProductNames();
        List<String> productPrices = checkoutPage.getSummaryProductPrices();
        List<String> productQuantities = checkoutPage.getSummaryProductQuantities();

        // Check that the summary has at least one item
        Assert.assertFalse(productNames.isEmpty(), "Order summary has no products!");

        // verify first product details
        Assert.assertEquals(productNames.get(0), "Sauce Labs Backpack", "Product name mismatch!");
        Assert.assertEquals(productPrices.get(0), "$29.99", "Product price mismatch!");
        Assert.assertEquals(productQuantities.get(0), "1", "Product quantity mismatch!");

        System.out.println("Order summary products: " + productNames);
    }


    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

