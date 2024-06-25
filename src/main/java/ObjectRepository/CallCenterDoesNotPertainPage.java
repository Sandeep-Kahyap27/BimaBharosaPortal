package ObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CallCenterDoesNotPertainPage {

    @FindBy(xpath = "(//*[text()='Does Not Pertain'])[1]")
    @CacheLookup
    private WebElement doesNotPertainDropDown;

    @FindBy(xpath = "(//*[text()='Does Not Pertain'])[3]")
    @CacheLookup
    private WebElement doesNotPertainPage;

    @FindBy(xpath = "//select[@name='DoesNotPertainDataTables_length']")
    @CacheLookup
    private WebElement dropDownTableEntity;

    @FindBy(xpath = "//*[contains(text(),'Showing 1 to 10')]")
    @CacheLookup
    private WebElement oneTo10TableEntity;

    @FindBy(xpath = "//*[contains(text(),'Showing 1 to 50')]")
    @CacheLookup
    private WebElement oneTo50TableEntity;

    @FindBy(xpath = "//*[contains(text(),'Showing 11 to 20')]")
    @CacheLookup
    private WebElement elevenTo20TableEntity;

    @FindBy(xpath = "//*[@id='DoesNotPertainDataTables_next']")
    @CacheLookup
    private WebElement next;

    @FindBy(xpath = "//*[@id='DoesNotPertainDataTables_previous']")
    @CacheLookup
    private WebElement previous;

    @FindBy(xpath = "//table[@id='DoesNotPertainDataTables']/tbody/tr[1]/td[1]/a[1]/i[1]")
    @CacheLookup
    private WebElement editBtn;

    @FindBy(xpath = "(//*[text()='Update Insurer'])[2]")
    @CacheLookup
    private WebElement updateInsurer;

    @FindBy(xpath = "//*[@id='btnUpdate']")
    @CacheLookup
    private WebElement updateBtn;

    @FindBy(xpath = "//*[@id='btncancel']")
    @CacheLookup
    private WebElement backBtn;

    @FindBy(xpath = "//span[text()='Complaint Number:']/following-sibling::strong")
    @CacheLookup
    private WebElement complaintNumIDToken;

    @FindBy(xpath = "//input[@id='txtentity']")
    @CacheLookup
    private WebElement selectedInsCompany;

    @FindBy(xpath = "//*[@id='ddlinsurancecomp_chosen']")
    @CacheLookup
    private WebElement nameInsurancCompany;

    @FindBy(xpath = "//button[@id='btnConfirmSuccess']")
    @CacheLookup
    private WebElement yesButton;

    @FindBy(xpath = "//h4[@id='successMessage']")
    @CacheLookup
    private WebElement successfullyPopUp;

    @FindBy(xpath = "//button[@id='btnsuccessModal']")
    @CacheLookup
    private WebElement okBtn;

    @FindBy(xpath = "//*[text()=' Entity Name']/following::td[7]")
    @CacheLookup
    private WebElement modifyInsCompanyName;

    @FindBy(xpath = "//*[@id='navbardrop']")
    @CacheLookup
    private WebElement complaintDropDown;

    static WebDriver driver = null;

    public CallCenterDoesNotPertainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clkDoesNotPertain() {
        doesNotPertainDropDown.click();
    }

    public boolean displayDoesNotPertain() {
        doesNotPertainPage.isDisplayed();
        return doesNotPertainPage.isDisplayed();
    }

    public void selectDefaultTableEntity() {
        Select dropDownEntity = new Select(dropDownTableEntity);
        dropDownEntity.selectByValue("10");

    }

    public void selectSpecificTableEntity() {
        Select dropDownEntity = new Select(dropDownTableEntity);
        dropDownEntity.selectByValue("50");
    }

    public boolean displayOneTo10TableEntity() {
        oneTo10TableEntity.isDisplayed();
        return oneTo10TableEntity.isDisplayed();

    }

    public boolean displayoneTo50TableEntity() {
        oneTo50TableEntity.isDisplayed();
        return oneTo50TableEntity.isDisplayed();
    }

    public boolean displayElevenTo20TableEntity() {
        elevenTo20TableEntity.isDisplayed();
        return elevenTo20TableEntity.isDisplayed();
    }

    public boolean displayUpdateInsurer() {
        updateInsurer.isDisplayed();
        return updateInsurer.isDisplayed();
    }

    public void clkNext() {
        next.click();
    }

    public void clkPrevious() {
        previous.click();
    }

    public void clkEditBtn() {
        editBtn.click();
    }

    public boolean updateButtonClickable() {
        try {
            // Wait until the button is visible and clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
            return button != null && button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean backButtonClickable() {
        try {
            // Wait until the button is visible and clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(backBtn));
            return button != null && button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTextComplaintNumIDToken() {
        complaintNumIDToken.getText();
        return complaintNumIDToken.getText();
    }

    public String getAttributeSelectedInsCompany() {
        String attribute = selectedInsCompany.getAttribute("value");
        return attribute;
    }

    public void clkNameInsurancCompany() {
        nameInsurancCompany.click();
    }

    public void clkUpdateBtn(){
        updateBtn.click();
    }

    public void clkYesButton(){
        yesButton.click();
    }

    public String getTextSuccessfullyPopUp(){
        successfullyPopUp.getText();
        return successfullyPopUp.getText();
    }

    public void clkOkBtn(){
        okBtn.click();
    }
    public String getTextModifyInsCompanyName(){
        modifyInsCompanyName.getText();
        return modifyInsCompanyName.getText();
    }

    public void clkComplaintDropDown(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", complaintDropDown);
    }

}
