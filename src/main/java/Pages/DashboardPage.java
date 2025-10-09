package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPage {
    
    private WebDriver driver;

    // Locators
    @FindBy(xpath = "//app-common-modal[@class='component-host-scrollable']")
    private WebElement allLoans_LoanDetails_Popup;

    @FindBy(xpath = "//a[normalize-space()='Bid Requests']")
    private WebElement bidRequest;

    @FindBy(xpath = "//*[normalize-space()='Commitments']")
    private WebElement commitments;

    @FindBy(xpath = "//span[normalize-space()='Price Offered']")
    private WebElement priceOffered;

    @FindBy(xpath = "//h3[normalize-space()='Price Offered']")
    private WebElement priceOffered_Heading;

    @FindBy(xpath = "//span[@aria-live='polite' and contains(text(),'Page 1 of ')]")
    private WebElement priceOffered_Pages;

    @FindBy(xpath = "//*[@aria-label='Go to Next Page']")
    private WebElement nextPageArrow;

    @FindBy(xpath = "//*[@aria-label='Status: Committed']")
    private WebElement committedStatus;

    @FindBy(xpath = "//div[@aria-label='Status: Committed']//span[normalize-space(text()='Committed')]/../../../td[@data-title='Bid Req. ID']/a")
    private WebElement committedStatus_BidRequestId;

    @FindBy(xpath = "//div[text()='Bid Req. ID']/following-sibling::h5")
    private WebElement priceOffered_BidRequestId_Heading;

    @FindBy(xpath = "//div[text()='Bid Loan Number']/following-sibling::h5")
    private WebElement bidLoanNumber;
    
    @FindBy(xpath = "//span[text()=\"Close\"]")
    private WebElement Close;

    @FindBy(xpath = "(//*[normalize-space(text())='All Loans'])[1]")
    private WebElement allLoans;

    @FindBy(xpath = "(//span[contains(@class,'fa fas fa-lock lock-icon')]/../following-sibling::td[@data-title='Corr. Loan#']/div)[1]")
    private WebElement lockedLoans;

    @FindBy(xpath = "//div[@class='apply-grid']/div/following-sibling::div[@class='border-bottom p-2 tr-value']")
    private List<WebElement> loanDetails_ValueColumn;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "(//*[normalize-space(text())='Locked/Committed Loans'])[1]")
    private WebElement locked_CommittedLoans;

    @FindBy(xpath = "(//td[@data-title='Corr. Loan#']//div//button)[1]")
    private WebElement locked_Committed_CorrLoan;

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Actions
    public void CommitmentsPriceOffered() {
        commitments.click();
        Assert.assertTrue(priceOffered.isDisplayed(), "Price Offered field not visible");
        priceOffered.click();

        Assert.assertTrue(priceOffered_Heading.isDisplayed(), "Price Offered heading not visible");

        String pagesText = priceOffered_Pages.getText();
        String pageCount = pagesText.split(" ")[3];
        int totalPages = Integer.parseInt(pageCount);

        for (int i = 0; i < totalPages; i++) {
            if (committedStatus.isDisplayed()) {
                String bidRequestId = committedStatus_BidRequestId.getText();
                System.out.println("The Bid Request Id with Committed Status is : " + bidRequestId);

                committedStatus_BidRequestId.click();
                String priceOffered_BidRequestId = priceOffered_BidRequestId_Heading.getText();
                System.out.println("The Bid Request Id in Price Offered Page is : " + priceOffered_BidRequestId);

                Assert.assertEquals(bidRequestId, priceOffered_BidRequestId, "Bid Request IDs do not match!");
                
                
               // Close.click();
                	allLoans.click();
                lockedLoans.click();

                String corrLoan = lockedLoans.findElement(By.tagName("button")).getText();
                System.out.println("The Corresponding Loan Id Div is : " + corrLoan);

                Assert.assertTrue(allLoans_LoanDetails_Popup.isDisplayed(), "Loan details popup not visible");
                Assert.assertEquals(bidLoanNumber.getText(), corrLoan, "Loan Number mismatch!");

              List<String> loanDetails_ValueList = new ArrayList<>();
                for (int j = 0; j < loanDetails_ValueColumn.size(); j++) {
                    WebElement detailElement = loanDetails_ValueColumn.get(j);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailElement);

                    String loanDetails = detailElement.getText().trim();
                    if (loanDetails.isEmpty()) {
                        loanDetails = "Null Value";
                    }
                    loanDetails_ValueList.add(loanDetails);
                    System.out.println("Loan Details List " + (j + 1) + " : " + loanDetails);
                }

                System.out.println(loanDetails_ValueList);

                closeButton.click();

                // Locked/Committed Loans validation
                locked_CommittedLoans.click();
                locked_Committed_CorrLoan.click();

                //Assert.assertTrue(allLoans_LoanDetails_Popup.isDisplayed(), "Popup not visible after locked loan click");
                Assert.assertEquals(bidLoanNumber.getText(), corrLoan, "Loan Number mismatch in Locked/Committed Loans!");

                for (int k = 0; k < loanDetails_ValueColumn.size(); k++) {
                    WebElement detailElement = loanDetails_ValueColumn.get(k);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailElement);

                    String loanDetails_LockedCommitted = detailElement.getText().trim();
                    System.out.println("Loan Details List K " + (k + 1) + " : " 
                        + loanDetails_LockedCommitted + " " + loanDetails_ValueList.get(k));

                    // Compare values
                   // Assert.assertEquals(loanDetails_LockedCommitted, loanDetails_ValueList.get(k),
                          //  "Mismatch in loan details between All Loans and Locked/Committed Loans!");
                }

                closeButton.click();
                break;
            } else {
                nextPageArrow.click();
            }
        }
    }
}
