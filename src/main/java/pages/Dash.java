package pages;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Dash {

    private WebDriver driver;
    private WebDriverWait wait;

    // ------------------ Locators ------------------
    @FindBy(xpath = "//span[text()='Commitments']")
    private WebElement commitments;

    @FindBy(xpath = "//span[text()='Price Offered']")
    private WebElement priceOfferedBy;

    @FindBy(xpath = "//h4[text()='Price Offered']")
    private WebElement priceOffered_Heading;

    @FindBy(xpath = "//button[@aria-label='All Loans']")
    private WebElement allLoans;

    @FindBy(xpath = "//button[@aria-label='Locked Loans']")
    private WebElement lockedLoans;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-right']")
    private WebElement nextPageArrow;

    @FindBy(xpath = "//span[@aria-live='polite' and contains(text(),'Page 1 of ')]")
    private WebElement priceOffered_Pages;

    @FindBy(xpath = "//button[@aria-label='Locked/Committed Loans']")
    private WebElement locked_CommittedLoans;

    @FindBy(xpath = "//button[@aria-label='Locked/Committed Corr Loan']")
    private WebElement locked_Committed_CorrLoan;

    // Dynamic locators
    private By committedStatusBy = By.xpath("//*[@aria-label='Status: Committed']");
    private By allLoansModalBy = By.xpath("//app-common-modal[@class='component-host-scrollable']");
    private String loanDetailsRelativeXPath = ".//div[@class='apply-grid']/div/following-sibling::div[@class='border-bottom p-2 tr-value']";

    // ------------------ Constructor ------------------
    public Dash(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // ------------------ Main Test Method ------------------
    public void CommitmentsPriceOffered_Safer() {
        wait.until(ExpectedConditions.elementToBeClickable(commitments)).click();
        wait.until(ExpectedConditions.elementToBeClickable(priceOfferedBy)).click();
        wait.until(ExpectedConditions.visibilityOf(priceOffered_Heading));

        String pagesText = wait.until(ExpectedConditions.visibilityOf(priceOffered_Pages)).getText();

        // Extract number of pages
        Pattern p = Pattern.compile("Page\\s+1\\s+of\\s+(\\d+)");
        Matcher m = p.matcher(pagesText);
        int totalPages = 1;
        if (m.find()) {
			totalPages = Integer.parseInt(m.group(1));
		}

        for (int i = 0; i < totalPages; i++) {
            List<WebElement> committedRows = driver.findElements(committedStatusBy);
            if (!committedRows.isEmpty()) {
                WebElement firstCommitted = committedRows.get(0);
                WebElement bidReqAnchor = firstCommitted.findElement(By.xpath(".//../../..//td[@data-title='Bid Req. ID']/a"));
                String bidRequestId = bidReqAnchor.getText().trim();
                System.out.println("Committed BidReq: " + bidRequestId);
                bidReqAnchor.click();

                WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[text()='Bid Req. ID']/following-sibling::h5")));
                String priceOfferedBidReq = heading.getText().trim();
                Assert.assertEquals(bidRequestId, priceOfferedBidReq);

                // Open All Loans → Locked Loans
                wait.until(ExpectedConditions.elementToBeClickable(allLoans)).click();
                wait.until(ExpectedConditions.elementToBeClickable(lockedLoans)).click();
                WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(allLoansModalBy));

                List<WebElement> loanDetailsElems = popup.findElements(By.xpath(loanDetailsRelativeXPath));
                List<String> values = new ArrayList<>();
                for (WebElement e : loanDetailsElems) {
                    wait.until(ExpectedConditions.visibilityOf(e));
                    String txt = e.getText().trim();
                    values.add(txt.isEmpty() ? "Null Value" : txt);
                }
                System.out.println(values);
                wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();

                // Locked/Committed Loans check
                wait.until(ExpectedConditions.elementToBeClickable(locked_CommittedLoans)).click();
                wait.until(ExpectedConditions.elementToBeClickable(locked_Committed_CorrLoan)).click();

                WebElement popup2 = wait.until(ExpectedConditions.visibilityOfElementLocated(allLoansModalBy));
                List<WebElement> lockedDetails = popup2.findElements(By.xpath(loanDetailsRelativeXPath));
                for (int k = 0; k < lockedDetails.size(); k++) {
                    String lockedTxt = lockedDetails.get(k).getText().trim();
                    if (lockedTxt.isEmpty()) {
						lockedTxt = "Null Value";
					}
                    Assert.assertEquals(lockedTxt, values.get(k), "Mismatch at index " + k);
                }
                wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
                break;

            } else {
                // Move to next page
                wait.until(ExpectedConditions.elementToBeClickable(nextPageArrow)).click();
                wait.until(ExpectedConditions.stalenessOf(priceOffered_Pages));
                wait.until(ExpectedConditions.visibilityOf(priceOffered_Pages));
            }
        }
    }
}
