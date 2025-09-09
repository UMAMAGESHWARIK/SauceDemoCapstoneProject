package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
	
	// Thread-safe WebDriver instance for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize WebDriver
    public static void initDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            // Open browser in incognito mode
            options.addArguments("--incognito");
            driver.set(new ChromeDriver(options));
            driver.get().manage().window().maximize();
        }
    }

 
    // Get WebDriver
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    // Quit WebDriver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

