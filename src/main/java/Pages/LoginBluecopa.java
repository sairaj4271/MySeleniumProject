package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBluecopa {
	private WebDriver driver;

    // Locators
    private By loginMenu = By.xpath("(//div[text()='Login'])[1]");
    private By usernameInput = By.id("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");

    // Constructor
    public LoginBluecopa(WebDriver driver) {
        this.driver = driver;
    }

    
    public void gotoLoginPage(String baseUrl) {
        driver.get(baseUrl);
        driver.findElement(loginMenu).click();
    } 

   
    
    // Perform login
    public void login(String username, String password) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);

        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(loginButton).click();
    }
}

