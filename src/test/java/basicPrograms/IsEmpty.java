package basicPrograms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IsEmpty {

	public static void main(String[] args) throws InterruptedException { 

		
			
			

	        	WebDriver driver = new ChromeDriver();

				driver.get("https://showcase.bluecopa.com/welcome");

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

				
				
				driver.findElement(By.xpath("//div[text()='Login']")).click();
				

				
				driver.findElement(By.id("username")).sendKeys("auto-testadmin@bluecopa.com");
				driver.findElement(By.id("password")).sendKeys("Admin@copa123");
				driver.findElement(By.name("action")).click();
				
				//driver.get("https://showcase.bluecopa.com/catalog/0UgWxDLLb65bUceFrV6G_1742971600243_dataset_28229_json");
				   
				  //Thread.sleep(1000);
				  
				  //WebElement textField =driver.findElement(By.xpath("(//div[@role=\"gridcell\"])[3]")); // Change locator accordingly

		            
		           // String fieldText = textField.getText().trim(); 

		            
		           // if (fieldText.isEmpty()) {
		               // System.out.println("The text field is empty (using innerText).");
		           // } else {
		               // System.out.println("The text field is NOT empty. Text: " + fieldText);
		           // }
		            
		            //.quit();
	}

}
