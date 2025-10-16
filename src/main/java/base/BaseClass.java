package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    protected static WebDriver driver;
    protected static Properties properties;
    protected static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        loadConfig();

        String browser = properties.getProperty("browser").toLowerCase().trim();
        //String appUrl = properties.getProperty("URL");
        int implicitWait = Integer.parseInt(properties.getProperty("ImplicitWait", "30"));
        int explicitWait = Integer.parseInt(properties.getProperty("ExplicitWait", "30"));

        //  Initialize WebDriver based on browser name
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        //  Browser configuration
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        //driver.get(appUrl);

        //  Explicit wait setup
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        System.out.println("Launched " + browser.toUpperCase() + " browser");
        //System.out.println(" Navigated to: " + appUrl);
    }

    // Load configuration safely
    private void loadConfig() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }

    //  Utility to get explicit wait instance anywhere
    public static WebDriverWait getWait() {
        return wait;
    }

    //  Utility to get current WebDriver instance
    public static WebDriver getDriver() {
        return driver;
    }
}
