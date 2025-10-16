package pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.netty.handler.timeout.TimeoutException;




public class Filebox extends base.BaseClass {
	private WebDriver driver;


	private By operations = By.xpath("(//a[@href='/operations/databox']//*[local-name() = 'svg'])[1]");
	private By filebox = By.xpath("//p[normalize-space()='Filebox']");
	private By newButton = By.xpath("(//div[normalize-space()='New'])[last()]");
	private By folderName = By.id("input_name");
	private By saveButton = By.xpath("//div[normalize-space()='Save']");
	private By nextButton = By.xpath("//div[normalize-space()='Next']");
	private By fileInput = By.xpath("//input[@type='file']");
	private By createButton = By.xpath("//div[normalize-space()='Create']");
	private By Refresh = By.xpath("//*[text()=\"Refresh\"]");
	private By Filedropped =By.xpath("//p[@data-test-id=\"listing-item-title-text\"]");
    private By spinner = By.xpath("//div[contains(@class, 'fixed') and contains(@class, 'flex') and contains(@class, 'backdrop-blur-xs') and contains(@class, 'block')]//*[local-name() = 'svg']");
    private By uploadeButton= By.xpath("(//div[text()[normalize-space() = \"Upload\"]])[1]]");
	private String generatedFileboxName;

	// Constructor
	public Filebox(WebDriver driver) {
		this.driver = driver;
	}

	// Utility: Generate random string
	public String generateRandomString(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result.append(chars.charAt(random.nextInt(chars.length())));
		}
		return result.toString();
	}
	public void waitForSpinnerToDisappear(By spinner, int timeoutInMillis) {
	    try {
	        
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
	        System.out.println("Spinner disappeared successfully.");
	    } catch (TimeoutException e) {
	        System.out.println("Spinner did not disappear within the timeout period.");
	    } catch (NoSuchElementException e) {
	        // Spinner not found — assume it’s already hidden
	        System.out.println("Spinner element not found (already hidden).");
	    }
	}



	public void navigateToFilebox() {
		driver.findElement(operations).click();
		driver.findElement(filebox).click();
		waitForSpinnerToDisappear(spinner, 10000);
            
	}


	public void createFilebox() {
		driver.findElement(newButton).click();

		WebElement folderInput = driver.findElement(folderName);
		generatedFileboxName = "Filebox_" + generateRandomString(6);

		folderInput.clear();
		folderInput.sendKeys(generatedFileboxName);


		 //driver.findElement(saveButton).click();
	}
	public void saveFilebox() {
		driver.findElement(saveButton).click();
	}
	public String getGeneratedFileboxName() {
		return generatedFileboxName;
	}
	public void uploadFileInFilebox(String filePath) {
	    Assert.assertTrue(driver.findElement(Refresh).isDisplayed());
		driver.findElement(fileInput).sendKeys(filePath);
		 driver.findElement(uploadeButton).click();
		 while (driver.findElement(Refresh).isEnabled()) {
			    WebElement fileDroppedElement = driver.findElement(Filedropped);

			    if (!fileDroppedElement.isDisplayed()) {
			        driver.findElement(Refresh).click();
			       
			    } else {
			        Assert.assertTrue(fileDroppedElement.isDisplayed(), "File dropped element is visible!");
			        break;
			    }
			}

			 
		}

	}

	// Additional methods for interacting with the Filebox page can be added here
