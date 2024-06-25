package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CallcenterViewEditPage {

    static WebDriver driver = null;


    @FindBy(xpath = "//*[@id='sidebarCollapse']")
    @CacheLookup
    private WebElement menuButton;

    @FindBy(xpath = "//*[@id='navbardrop']")
    @CacheLookup
    private WebElement complaintDropDown;

    @FindBy(xpath = "(//*[contains(text(),'View / Edit Complaints')])[1]")
    @CacheLookup
    private WebElement viewEditDropDown;

    @FindBy(xpath = "//button[@id='btnsearch']")
    @CacheLookup
    private WebElement searchButton;

    @FindBy(xpath = "//button[@id='btnclear']")
    @CacheLookup
    private WebElement clearButton;

    @FindBy(xpath = "//button[@id='btnExport']")
    @CacheLookup
    private WebElement exportToExcelButton;

    @FindBy(xpath = "//button[@id='btnfirst']")
    @CacheLookup
    private WebElement firstButton;

    @FindBy(xpath = "//button[@id='btnnext']")
    @CacheLookup
    private WebElement nextButton;

    @FindBy(xpath = "//button[@id='btnlast']")
    @CacheLookup
    private WebElement lastButton;

    @FindBy(xpath = "//button[@id='btnprev']")
    @CacheLookup
    private WebElement previousButton;

    @FindBy(xpath = "//*[@id='IrdaTokenMumber']")
    @CacheLookup
    private WebElement complaintNumber;

    @FindBy(xpath = "//*[@id='EntityReferenceNumber']")
    @CacheLookup
    private WebElement entityReferenceNumber;

    @FindBy(xpath = "//*[@id='policyNumber']")
    @CacheLookup
    private WebElement policyNumber;

    @FindBy(xpath = "//*[@id='complainantName']")
    @CacheLookup
    private WebElement complainantName;

    @FindBy(xpath = "//*[@id='searchByMobileNumber']")
    @CacheLookup
    private WebElement mobileNumberViewEditPage;

    @FindBy(xpath = "//*[@id='warningMessage']")
    @CacheLookup
    private WebElement errEmptySearch;

    @FindBy(xpath = "//*[@id='btnwarningModal']")
    @CacheLookup
    private WebElement ok;

    @FindBy(xpath = "//*[@id='VieweditDataTables_info']")
    @CacheLookup
    private WebElement showingEntity;

    @FindBy(xpath = "//*[text()='06-24-000615']")
    @CacheLookup
    private WebElement validComplaintNumber;

    @FindBy(xpath = "//*[text()='HDFCG005915']")
    @CacheLookup
    private WebElement validEntityReferenceNumber;

    @FindBy(xpath = "//*[text()='121231']")
    @CacheLookup
    private WebElement validPolicyNumber;

    @FindBy(xpath = "//*[text()='MR SANDEEP KASHYAP']")
    @CacheLookup
    private WebElement validComplainantName;

    @FindBy(xpath = "//*[@id='VieweditDataTables_info']")
    @CacheLookup
    private WebElement showingEntityForMobNum;

    @FindBy(xpath = "//table[@id='VieweditDataTables']/tbody/tr[1]/td[2]/a[1]/i[1]")
    @CacheLookup
    private WebElement editIcon;

    @FindBy(xpath = "//*[text()='Update Complaints']")
    @CacheLookup
    private WebElement updateComplaints;

    @FindBy(xpath = "//*[@id='btncancel']")
    @CacheLookup
    private WebElement cancelbtn;

    @FindBy(xpath = "//*[text()='View Complaints']")
    @CacheLookup
    private WebElement viewEdit;


    public CallcenterViewEditPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clkMenuButton() {
        menuButton.click();
    }

    public void clkComplaintDropDown() {
        complaintDropDown.click();

    }

    public void clkViewEditDropDown() {
        viewEditDropDown.click();
    }

    public void clkEditIcon() {
        editIcon.click();
    }

    public void clkCancelBtn() {
        cancelbtn.click();
    }

    public boolean displayCancelBtn() {
        cancelbtn.isDisplayed();
        return cancelbtn.isDisplayed();
    }

    public boolean displayViewEdit() {
        viewEdit.isDisplayed();
        return viewEdit.isDisplayed();
    }

    public boolean displayUpdateComplaints() {
        updateComplaints.isDisplayed();
        return updateComplaints.isDisplayed();
    }

    public boolean displaySearchButton() {
        searchButton.isDisplayed();
        return searchButton.isDisplayed();

    }

    public boolean displayClearButton() {
        clearButton.isDisplayed();
        return clearButton.isDisplayed();
    }

    public boolean displayExportToExcel() {
        exportToExcelButton.isDisplayed();
        return exportToExcelButton.isDisplayed();
    }

    public boolean displayFirstButton() {
        firstButton.isDisplayed();
        return firstButton.isDisplayed();

    }

    public boolean displayNextButton() {
        nextButton.isDisplayed();
        return nextButton.isDisplayed();
    }

    public boolean displayLastButton() {
        lastButton.isDisplayed();
        return lastButton.isDisplayed();
    }

    public boolean displayPreviousButton() {
        previousButton.isDisplayed();
        return previousButton.isDisplayed();
    }

    public void clkNextButton() {
        nextButton.click();
    }

    public void clrComplaintNumber() {
        complaintNumber.clear();
    }

    public void clrEntityReferenceNumber() {
        entityReferenceNumber.clear();
    }

    public void clrPolicyNumber() {
        policyNumber.clear();
    }

    public void clrComplainantName() {
        complainantName.clear();
    }

    public void clrMobileNum() {
        mobileNumberViewEditPage.clear();
    }

    public void enterComplaintNumber(String complaintNum) {
        complaintNumber.sendKeys(complaintNum);
    }

    public void enterEntityReferenceNumber(String entityReferenceNum) {
        entityReferenceNumber.sendKeys(entityReferenceNum);

    }

    public void enterPolicyNumber(String policyNum) {
        policyNumber.sendKeys(policyNum);

    }

    public void enterComplainantName(String complaintNa) {
        complainantName.sendKeys(complaintNa);

    }

    public void enterMobileNumberViewEditPage(String mob) {
        mobileNumberViewEditPage.sendKeys(mob);

    }

    public void clkClearBtn() {
        clearButton.click();
    }

    public void clkSearchBtn() {
        searchButton.click();
    }

    public String getTextErrEmptySearch() {
        errEmptySearch.getText();
        return errEmptySearch.getText();
    }

    public void clkOk() {
        ok.click();
    }

    public String getTextShowingEntityZero() {
        showingEntity.getText();
        return showingEntity.getText();
    }

    public String getTextValidComplaintNumber() {
        validComplaintNumber.getText();
        return validComplaintNumber.getText();

    }

    public String getTextValidEntityReferenceNumber() {
        validEntityReferenceNumber.getText();
        return validEntityReferenceNumber.getText();
    }

    public String getTextValidPolicyNumber() {
        validPolicyNumber.getText();
        return validPolicyNumber.getText();
    }

    public String getTextValidComplainantName() {
        validComplainantName.getText();
        return validComplainantName.getText();
    }

    public String getTextValidMobileNumOfTable() {
        showingEntityForMobNum.getText();
        return showingEntityForMobNum.getText();
    }

    public boolean getAttributePolicyNumber() {
        String comNum = policyNumber.getAttribute("value");
        if (comNum.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void scrollToDownWord() throws InterruptedException {

        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);

    }

    public void scrollToUpWord() throws InterruptedException {

        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-400)");
        Thread.sleep(2000);


    }

}
