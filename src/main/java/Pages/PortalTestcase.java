package Pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PortalTestcase {
    
    private WebDriver driver;
    private WebDriverWait wait; // declare here, initialize in constructor

    private By menu = By.xpath("//div[contains(@class, 'transition') and contains(@class, 'ease-in-out') and contains(@class, 'contrast-more')]//*[local-name() = 'svg']");
    private By search = By.xpath("(//input[@placeholder=\"Search\"])[last()]");
    private By portalname = By.xpath("(//p[text()[normalize-space() = \"Portals\"]])[1]");
    private By newbutton = By.xpath("//div[text()[normalize-space() = \"New\"]]");
    private By pencil = By.xpath("//div[contains(@class,\"text-secondary cursor-pointer\")]");
    private By portalfilename = By.xpath("//input[@id='input_text']");
    private By Applybutton = By.xpath("//div[text()[normalize-space() = \"Apply\"]]");
    private By savebutton = By.xpath("//div[text()[normalize-space() = \"Save\"]]");
    private String generatedPortalName;
    private By Toggle = By.xpath("//div[text()=\"Show Stencil\"]");
    private By spinner = By.xpath("//div[contains(@class, 'fixed') and contains(@class, 'flex') and contains(@class, 'backdrop-blur-xs') and contains(@class, 'block')]//*[local-name() = 'svg']");
    private By inputTablet = By.xpath("//p[text()=\"Input table\"]/..");
    private By widget = By.xpath("//p[text()=\"No widgets added\"]/..");

    // ✅ Fix here
    public PortalTestcase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // initialize AFTER driver is set
    }

    public String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(chars.length())));
        }
        return result.toString();
    }

    public void navigateToPortal() {
        driver.findElement(menu).click();
        driver.findElement(search).sendKeys("Portals");
        driver.findElement(portalname).click();
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    public void createPortal() {
        driver.findElement(newbutton).click();
        driver.findElement(pencil).click();
        generatedPortalName = "Portal_" + generateRandomString(6);
        driver.findElement(portalfilename).clear();
        driver.findElement(portalfilename).sendKeys(generatedPortalName);
        driver.findElement(Applybutton).click();
        driver.findElement(savebutton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        driver.findElement(Toggle).click();
        driver.findElement(savebutton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
      
    }

    public void InputTable() throws InterruptedException {
        Actions actions = new Actions(driver);

        WebElement source = driver.findElement(inputTablet);
        WebElement target = driver.findElement(widget);

        actions.moveToElement(source).perform();
        actions.moveToElement(target).perform();

        actions.clickAndHold(source)
                .pause(Duration.ofMillis(200))
                .moveToElement(target)
                .pause(Duration.ofMillis(200))
                .release()
                .build()
                .perform();

        String targetText = target.getText();
     
        
        Thread.sleep(10000); // Wait for UI to update
        //assert targetText.contains("Input table");
    }
}
