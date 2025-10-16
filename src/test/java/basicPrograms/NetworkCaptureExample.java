package basicPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;

import java.time.Duration;
import java.util.Optional;

public class NetworkCaptureExample {
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
		
		driver.get("https://ext-qa.lpcorrtest.com/cp/#/admin/bid-maps?page=0&size=20&sort=modifiedDate&order=desc");
		
		  Thread.sleep(10000);
		  
     // WebDriver drivers = new ChromeDriver();
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            System.out.println("➡️ URL: " + req.getUrl());

            if (req.getHeaders().toJson().containsKey("Authorization")) {
                System.out.println("🔐 Token: " + req.getHeaders().toJson().get("Authorization"));
            }
        });
              driver.quit();
        //driver.get("https://ext-qa.lpcorrtest.com/cp/#/admin/bid-maps?page=0&size=20&sort=modifiedDate&order=desc"); // Replace with your app URL
    }
}
