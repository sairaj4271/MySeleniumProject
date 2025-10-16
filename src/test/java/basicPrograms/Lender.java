package basicPrograms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lender {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.get("https://ext-qa.lpcorrtest.com/cp/#/auth/login");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		Thread.sleep(3000);

		
	
		
		driver.findElement(By.id("username")).sendKeys("testsigma_internal");
		driver.findElement(By.id("password")).sendKeys("Welcome@123");
		driver.findElement(By.xpath("//span[text()=\"Login\"]")).click();
		Thread.sleep(3000);
		
		driver.get("https://ext-qa.lpcorrtest.com/cp/#/admin/bid-maps/edit-mapping/6826fdc0dc17965524111af9/enumeration-mapping");
		
		  Thread.sleep(3000);
		  

           
            WebElement element = driver.findElement(By.xpath("(//select[contains(@class,\"form-select\")])[3]"));
            if (!element.isEnabled()) {
                System.out.println("Element is enabled");
            } else {
                System.out.println("Element is disabled");
            }
            Thread.sleep(2000);
            driver.close();

	}

}
