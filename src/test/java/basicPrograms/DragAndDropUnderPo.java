package basicPrograms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropUnderPo {

	public static void main(String[] args) throws InterruptedException { 
		
		
		

		WebDriver driver = new ChromeDriver();


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://showcase.bluecopa.com/welcome");

		Thread.sleep(3000);

		
		driver.findElement(By.xpath("//div[text()='Login']")).click();
		Thread.sleep(1000);

		
		driver.findElement(By.id("username")).sendKeys("auto-testadmin@bluecopa.com");
		driver.findElement(By.id("password")).sendKeys("Admin@copa123");
		driver.findElement(By.name("action")).click();
		Thread.sleep(1000);
		
		driver.get("https://showcase.bluecopa.com/apps/portal/M8PLRU7TO2NVPM461X7E");
		
		 WebElement draggableElement = driver.findElement(By.xpath("//p[text()=\"Title\"]"));

           
            WebElement target =driver.findElement(By.xpath("//div[contains(@class,\"w-full flex items-center justify-between col\")]"));
          
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", draggableElement);

            // Define X and Y offsets
            int xOffset = 300; // Change as needed
            int yOffset = 0; // Change as needed

            System.out.println("Dragging element by X: " + xOffset + " and Y: " + yOffset);

            // Perform drag-and-drop by offset
            Actions actions = new Actions(driver);
            actions.clickAndHold(draggableElement)
                   .pause(500) // Pause for stability
                   .moveByOffset(xOffset, yOffset)
                   .release()
                   .perform();
               
            System.out.println("✅ Element dragged successfully!");

	}

}
