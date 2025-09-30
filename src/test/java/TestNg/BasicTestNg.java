package TestNg;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTestNg {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void dragAndDropElementTest() throws InterruptedException {
        driver.get("https://showcase.bluecopa.com/welcome");
        Thread.sleep(3000);

        // Login
        driver.findElement(By.xpath("//div[text()='Login']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("auto-testadmin@bluecopa.com");
        driver.findElement(By.id("password")).sendKeys("Admin@copa123");
        driver.findElement(By.name("action")).click();
        Thread.sleep(1000);

        // Navigate to portal page
        driver.get("https://showcase.bluecopa.com/apps/portal/M9CQ7Z6CGEW3I1IREES8?portalPageId=M9CQ7U3N196GJERH1H21");

        // Wait and click Save
        Thread.sleep(8000);
        WebElement save = driver.findElement(By.xpath("//div[text()=\"Save\"]"));
        save.click();

        Thread.sleep(8000); // Wait for UI

        // Locate draggable element
        WebElement draggableElement = driver.findElement(By.xpath("//p[text()=\"Title\"]"));

        // Scroll to view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", draggableElement);

        // Calculate offsets
        Rectangle elementRect = draggableElement.getRect();
        int elementRightEdge = elementRect.getX() + elementRect.getWidth();
        int elementBottomEdge = elementRect.getY() + elementRect.getHeight();
        int xOffset = Math.min(350, driver.manage().window().getSize().getWidth() - elementRightEdge - 10);
        int yOffset = Math.min(0, driver.manage().window().getSize().getHeight() - elementBottomEdge - 10);

        System.out.println("Dragging element by X: " + xOffset + " and Y: " + yOffset);

        // Perform drag and drop
        Actions actions = new Actions(driver);
        actions.clickAndHold(draggableElement)
               .moveByOffset(xOffset / 2, yOffset / 2)
               .moveByOffset(xOffset / 2, yOffset / 2)
               .release()
               .perform();

        System.out.println("Element dragged successfully!");
    }
       @Test
       public void demoNew() throws InterruptedException {
    	   driver.get("https://showcase.bluecopa.com/welcome");
           Thread.sleep(3000);

           // Login
           driver.findElement(By.xpath("//div[text()='Login']")).click();
           Thread.sleep(1000);
           driver.findElement(By.id("username")).sendKeys("auto-testadmin@bluecopa.com");
           driver.findElement(By.id("password")).sendKeys("Admin@copa123");
           driver.findElement(By.name("action")).click();
           Thread.sleep(1000);
    	   driver.get("https://showcase.bluecopa.com/apps/portal/M9CQ7Z6CGEW3I1IREES8?portalPageId=M9CQ7U3N196GJERH1H21");

           // Wait and click Save
           Thread.sleep(8000);
           WebElement save = driver.findElement(By.xpath("//div[text()=\"Save\"]"));
           save.click();

           Thread.sleep(8000); // Wait for UI

           // Locate draggable element
           WebElement draggableElement = driver.findElement(By.xpath("//p[text()=\"Title\"]"));

           // Scroll to view
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", draggableElement);

           // Calculate offsets
           Rectangle elementRect = draggableElement.getRect();
           int elementRightEdge = elementRect.getX() + elementRect.getWidth();
           int elementBottomEdge = elementRect.getY() + elementRect.getHeight();
           int xOffset = Math.min(350, driver.manage().window().getSize().getWidth() - elementRightEdge - 10);
           int yOffset = Math.min(0, driver.manage().window().getSize().getHeight() - elementBottomEdge - 10);

           System.out.println("Dragging element by X: " + xOffset + " and Y: " + yOffset);

           // Perform drag and drop
           Actions actions = new Actions(driver);
           actions.clickAndHold(draggableElement)
                  .moveByOffset(xOffset / 2, yOffset / 2)
                  .moveByOffset(xOffset / 2, yOffset / 2)
                  .release()
                  .perform();

           System.out.println("Element dragged successfully!");
    	   
               @Nullable
			String currentUrl = driver.getCurrentUrl();
               System.out.println("for current"+ currentUrl);
    	         @Nullable
				String get = driver.getTitle();
    	         System.out.println("for current"+ get);
       }
       
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
