package basicPrograms;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

public class RandomColorPickerEveryRun {
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
		
	
		
        driver.get("https://showcase.bluecopa.com/settings/theme");

        try {
            // Load your page
            driver.get("https://showcase.bluecopa.com/settings/theme"); // Use a hosted URL for cloud testing

            Thread.sleep(1000); // Wait for page load

            // Find the color input element
            WebElement colorInput = driver.findElement(By.xpath("//input[@type=\"color\"]"));

            // Generate random hex color
            String randomColor = generateRandomHexColor();

            // Set the random color
            colorInput.sendKeys(randomColor);

            System.out.println("Color set to: " + randomColor);

            Thread.sleep(2000); // Wait to see the result

        } finally {
            driver.quit();
        }
    }

    // Utility to generate a random hex color (like #1A2B3C)
    public static String generateRandomHexColor() {
        Random random = new Random(System.currentTimeMillis()); // Ensures different seed each run
        int color = random.nextInt(0xFFFFFF + 1); // 0x000000 to 0xFFFFFF
        return String.format("#%06X", color);
    }
}
