package testcases;
import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductsPage;

@Listeners(utils.TestListener.class)
public class CheckOutTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productPage;
    CartPage cartPage;
    CheckOutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        // Login
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Add product and go to cart
        productPage = new ProductsPage();
        productPage.addFirstProductToCart();
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

        int itemCount = checkoutPage.getSummaryItemCount();
        Assert.assertFalse(itemCount < 0, "Order summary has no items!");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
