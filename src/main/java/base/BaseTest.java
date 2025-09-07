package base;

import utils.ExtentManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	ExtentManager extent;
	
    @BeforeSuite
    public void beforeSuiteSetup() {
        // Start extent report
        ExtentManager.initReports();
        System.out.println("=== Test Suite Started ===");
    }

    @AfterSuite
    public void afterSuiteTearDown() {
        // Close extent report
        ExtentManager.flushReports();
        System.out.println("=== Test Suite Finished ===");
    }
    
    @BeforeMethod
    public void setUp() {
    	// Initialize WebDriver instance and navigate to the login page
        DriverManager.initDriver();
        DriverManager.getDriver().get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
    	// Close the browser and clean up WebDriver
        DriverManager.quitDriver();
    }
}

