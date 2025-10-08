package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Filebox {
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

	
	public void navigateToFilebox() {
		driver.findElement(operations).click();
		driver.findElement(filebox).click();
	   
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
		while(!driver.findElement(Refresh).isEnabled()) {
			
			 ((WebElement) Refresh).click();
			 Assert.assertTrue(driver.findElement(Filedropped).isDisplayed());
		     break;
			
		}
		
	}

}
