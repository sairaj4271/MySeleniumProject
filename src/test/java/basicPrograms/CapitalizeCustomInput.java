package basicPrograms;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CapitalizeCustomInput {
    public static void main(String[] args) {
        // Your custom input value
        String input = "gelloWorld";

        // Capitalize first letter only
        String capitalized = "";
        if (input != null && input.length() > 0) {
            capitalized = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

        // Print the result
        System.out.println("Converted Word: " + capitalized);

        // OPTIONAL: Launch browser if needed
        //WebDriver driver = new ChromeDriver();
        //driver.get("https://example.com");

        // You can send this capitalized value to a text box if needed
        // driver.findElement(By.id("yourInputId")).sendKeys(capitalized);

        // Close browser
        //driver.quit();
    }
}
