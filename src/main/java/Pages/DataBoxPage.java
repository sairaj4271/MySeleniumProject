package Pages;


import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataBoxPage {
    private WebDriver driver;

    // Locators
    private By operations = By.xpath("(//a[@href='/operations/databox']//*[local-name() = 'svg'])[1]");
    private By databox = By.xpath("//p[normalize-space()='Databox']");
    private By newButton = By.xpath("(//div[normalize-space()='New'])[last()]");
    private By folderName = By.id("input_name");
    private By saveButton = By.xpath("//div[normalize-space()='Save']");
    private By nextButton = By.xpath("//div[normalize-space()='Next']");
    private By fileInput = By.xpath("//input[@type='file']");
    private By createButton = By.xpath("//div[normalize-space()='Create']");

    private String generatedFileboxName;

    // Constructor
    public DataBoxPage(WebDriver driver) {
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

    
    public void navigateToDatabox() {
        driver.findElement(operations).click();
        driver.findElement(databox).click();
       
    }

    
    public void createDatabox() {
        driver.findElement(newButton).click();

        WebElement folderInput = driver.findElement(folderName);
        generatedFileboxName = "Databox_" + generateRandomString(6);

        folderInput.clear();
        folderInput.sendKeys(generatedFileboxName);

       
         //driver.findElement(saveButton).click();
    }

    
    public void uploadFileInDatabox(String filePath) {
        driver.findElement(fileInput).sendKeys(filePath);
        driver.findElement(nextButton).click();
        driver.findElement(createButton).click();
    }

    
    public String getGeneratedFileboxName() {
        return generatedFileboxName;
    }
}
