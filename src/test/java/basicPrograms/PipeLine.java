package basicPrograms;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PipeLine {

	public static void main(String[] args) throws InterruptedException, AWTException { 
		
		
		

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
			
			driver.get("https://showcase.bluecopa.com/allocations-build/workflow/MDMLJB4JHZGILFSNE1OO");
			
			 WebElement sourceElement = driver.findElement(By.xpath("(//div[@data-handlepos=\"right\"])[2]"));
					 Thread.sleep(5000);
	           
	         WebElement targetElement=driver.findElement(By.xpath("//div[@data-handlepos=\"left\"]"));
	            
	            //sourceElement.click();
	           

	            Actions actions = new Actions(driver);
	            actions.clickAndHold(sourceElement).perform();
	            actions.dragAndDrop(sourceElement, targetElement).perform();
	           
	            
	            //targetElement.click();
	           

	}

}
