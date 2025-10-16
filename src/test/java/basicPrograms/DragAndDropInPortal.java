package basicPrograms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DragAndDropInPortal {

	public static void main(String[] args) throws InterruptedException { 
		
		
		

			WebDriver driver = new ChromeDriver();

			driver.get("https://showcase.bluecopa.com/welcome");

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			Thread.sleep(3000);

			
			driver.findElement(By.xpath("//div[text()='Login']")).click();
			Thread.sleep(1000);

			
			driver.findElement(By.id("username")).sendKeys("auto-testadmin@bluecopa.com");
			driver.findElement(By.id("password")).sendKeys("Admin@copa123");
			driver.findElement(By.name("action")).click();
			Thread.sleep(1000);
			
			driver.get("https://showcase.bluecopa.com/apps/portal/M9CQ7Z6CGEW3I1IREES8?portalPageId=M9CQ7U3N196GJERH1H21");
			
			

	           
	            WebElement target =driver.findElement(By.xpath("//p[text()=\"Title\"]"));
	            Thread.sleep(8000);
	            
	          
	               WebElement save = driver.findElement(By.xpath("//div[text()=\"Save\"]"));

	                                    save.click();
	            Thread.sleep(8000);
	            
	            WebElement draggableElement = driver.findElement(By.xpath("//p[text()=\"Title\"]"));

	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", draggableElement);

	            Rectangle elementRect = draggableElement.getRect();
	            int elementRightEdge = elementRect.getX() + elementRect.getWidth();
	            int elementBottomEdge = elementRect.getY() + elementRect.getHeight();

	           
	            int xOffset = Math.min(350, driver.manage().window().getSize().getWidth() - elementRightEdge - 10);
	            int yOffset = Math.min(0, driver.manage().window().getSize().getHeight() - elementBottomEdge - 10);

	            System.out.println("Dragging element by X: " + xOffset + " and Y: " + yOffset);
              
	            Actions actions = new Actions(driver);
	            actions.clickAndHold(draggableElement)
	                   .moveByOffset(xOffset / 2, yOffset / 2)
	                   .moveByOffset(xOffset / 2, yOffset / 2)
	                   .release()
	                   .perform();
     
	            
	                   

	            System.out.println(" Element dragged successfully!");

			                   
		
	}

}
