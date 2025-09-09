package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    // Initialize ExtentReports (only once)
    public static synchronized ExtentReports initReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
            spark.config().setReportName("SauceDemo Automation Report");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
    
    // Flush and write the report to the file
    public static synchronized void flushReports() {
        if (extent != null) extent.flush();
    }

    // Create a new test in the report and store it in ThreadLocal
    public static synchronized ExtentTest createTest(String name) {
        ExtentTest testInstance = extent.createTest(name);
        test.set(testInstance);
        return testInstance;
    }
    
    // Get the current test for the current thread
    public static ExtentTest getTest() {
        return test.get();
    }

    // Remove the test from ThreadLocal (cleanup after test)
    public static void removeTest() {
        test.remove();
    }
}

