package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;

public class ScreenshotUtil extends BaseClass implements ITestListener{

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            takeScreenshot(driver, result.getName());
        } else {
            System.out.println("Driver is null! Screenshot not captured.");
        }
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        try {
        	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("./screenshotsNew/image"+timestamp+".png");
            FileUtils.copyFile(src, dest);
            System.out.println("✅ Screenshot saved at: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
