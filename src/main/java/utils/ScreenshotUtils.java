package utils;

import base.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String takeScreenshot(String testName) {
        try {
            WebDriver driver = DriverManager.getDriver();
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = "screenshots/" + testName + "_" + ts + ".png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(path));
            return path;
        } catch (IllegalStateException e) {
            System.out.println("Screenshot skipped - driver not initialized.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

