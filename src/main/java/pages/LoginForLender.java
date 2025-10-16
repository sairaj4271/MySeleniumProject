package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginForLender {

    WebDriver driver;

    public LoginForLender(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By usernameField = By.xpath("//input[@id=\"username\"]");
    By passwordField = By.xpath("//input[@id=\"password\"]");
    By loginButton = By.xpath("//*[text()=\"Login\"]");

    // Navigate to URL
    public void navigateToURL() {
                      driver.get("https://ext-qa.lpcorrtest.com/cp/#/auth/login");
    }

    // Login method
    public void loginData() {
          driver.findElement(usernameField).clear();
          driver.findElement(usernameField).sendKeys("testsigma_internal");
          driver.findElement(passwordField).clear();
          driver.findElement(passwordField).sendKeys("Welcome@123");

          driver.findElement(loginButton).isEnabled();
              driver.findElement(loginButton).click();
           //if (loginButton!=isEna)


    }
}
