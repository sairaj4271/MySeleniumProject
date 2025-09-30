package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;

public class ScreenshotUtil implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseClass.getDriver();
        if (driver != null) {
            takeScreenshot(driver, result.getName());
        } else {
            System.out.println("Driver is null! Screenshot not captured.");
        }
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) screenshotDir.mkdir();

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotDir, testName + "_" + timestamp + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("✅ Screenshot saved at: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
