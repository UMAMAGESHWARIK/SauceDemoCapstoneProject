package utils;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.initReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        // Create ExtentTest and store in ThreadLocal
        if (ExtentManager.getTest() == null) {
            ExtentManager.createTest(name);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Test passed");
            String path = ScreenshotUtils.takeScreenshot(result.getMethod().getMethodName());
            if (path != null) ExtentManager.getTest().addScreenCaptureFromPath(path);
        }
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            // ensure ExtentTest exists
            if (ExtentManager.getTest() == null) {
                ExtentManager.createTest(result.getMethod().getMethodName());
            }

            // try to take screenshot (safe inside)
            String screenshotPath = ScreenshotUtils.takeScreenshot(result.getMethod().getMethodName());
            if (screenshotPath != null) {
                ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
            }

            ExtentManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        } catch (Exception e) {
            System.out.println("Listener handling failure threw exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ExtentManager.removeTest();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        }
        ExtentManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flushReports();
    }
}

