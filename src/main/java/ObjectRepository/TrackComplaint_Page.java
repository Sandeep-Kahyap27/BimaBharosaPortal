package ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.channels.ScatteringByteChannel;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrackComplaint_Page {

    WebDriver driver;
    @FindBy(id = "txtotp1")
    private WebElement OTP;
    @FindBy(id = "btnResend")
    private WebElement Resend;

    //ViewUpdate/PHViewEditComplaint
    @FindBy(xpath = "//table[@id='PHVieweditDataTables']/thead[1]")
    private WebElement visibility_table_heading;
    @FindBy(xpath = "//span/i[@class='fa fa-file-excel-o']")
    private WebElement click_on_export;

    @FindBy(xpath = "//h4[text()='Registered Complaints']")
    private WebElement visible_track_complaint;

    @FindBy(id = "sidebarCollapse")
    private WebElement menu;

    @FindBy(id = "navbardrop")
    private WebElement complaints;

    @FindBy(xpath = "//a[text()='Register New Complaint Against Entity']")
    private WebElement Reg_New_Complaint;

    @FindBy(xpath = "//a[text()='View / Edit Complaints']")
    private WebElement View_Edit_Complaints;

    @FindBy(xpath = "//select[@name='PHVieweditDataTables_length']")
    private WebElement entries;

    @FindBy(xpath = "(//a[@href='javascript:void(0);'])[1]")
    private WebElement view_edit_button_click;

    @FindBy(xpath = "//table[@id='PHVieweditDataTables']/tbody/tr[1]/td[9]")
    private WebElement complaint_register;

    //PolicyholderUpdateComplaint-
    @FindBy(id = "txtcompldetails")
    private WebElement Complaint_Description;

    @FindBy(id = "btnupdate")
    private WebElement update;

    @FindBy(id = "btncancel")
    private WebElement cancel;

    @FindBy(xpath = "//h4[text()='View / Edit Complaints']")
    private WebElement visible_view_edit;

    @FindBy(id = "btnConfirmSuccess")
    private WebElement selectyes;

    @FindBy(id = "btncancel")
    private WebElement selectcancel;

    @FindBy(id = "closeIcon")
    private WebElement closemenubtn;

    @FindBy(xpath = "//a[@class='label_check_uploaded_docs']")
    private WebElement view_doc;

    @FindBy(xpath = "(//button[@class='close'])[1]")
    private WebElement view_doc_close;


    @FindBy(xpath = "(//h5[@id='exampleModalLabel'])[1]")
    private WebElement visibility_uploaded_doc;

    @FindBy(id = "btnsuccessModal")
    private WebElement click_ok;

    @FindBy(id = "ddlportalfeedback_chosen")
    private WebElement feedbackForIGMSPortalEaseOfUse;
    @FindBy(id = "ddlresolutinonfeedback_chosen")
    private WebElement resolutionSatisfactionLevel;

    @FindBy(xpath = "//span[text() = 'Logout']")
    private WebElement logout;

    @FindBy(id = "btnConfirmSuccess")
    private WebElement update_record_success;

    public TrackComplaint_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*Action Methods*/
    public void wait_for_60s() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(Resend));
    }

    public void ResendBtnClk() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", Resend);
        Thread.sleep(2000);
    }

    //ViewUpdate/PHViewEditComplaint
    public void MenuBtnClk() throws InterruptedException {
        Thread.sleep(3000);
        menu.click();
    }

    public void complaintClk() throws InterruptedException {
        Thread.sleep(3000);
        complaints.click();
    }

    public void newRegisterClick() throws InterruptedException {
        Thread.sleep(1000);
        Reg_New_Complaint.click();

    }

    public void scrollTrackingPage() throws InterruptedException {

        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(3000);

    }

    public void clearField_complaint_desc() throws InterruptedException {
        // Complaint_Description.click();
        Thread.sleep(3000);
        Complaint_Description.clear();
    }

    public void click_ok() {

        click_ok.click();
    }

    public void click_on_export() {

        click_on_export.click();
    }

    public void close_menu_btn() {

        closemenubtn.click();
    }

    public void RegNewCompl(String Reg_New_Complaint) {

        this.Reg_New_Complaint.click();
    }

    public void ViewEditCompl() {
        this.View_Edit_Complaints.click();
    }

    public void Entires(String entries) throws InterruptedException {
        Thread.sleep(2000);
        this.entries.click();

    }

    public void view_edit_button_click() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", view_edit_button_click);
        Thread.sleep(3000);
    }

    public void uploaded_document_click() {
        view_doc.click();
    }

    public void uploaded_document_close() {
        view_doc_close.click();
    }

    public void enter_ComplaintDesc(String input) {

        Complaint_Description.sendKeys(input);
    }

    public void update_complaint_desc() throws InterruptedException {
        Thread.sleep(3000);
        update.click();
    }

    public void SelectYesBtn_click() {

        selectyes.click();
    }

    public void SelectCancelBtn_click() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(3000);
        selectcancel.click();
    }
    public void handleDropdown(WebElement dropdown, int index) throws InterruptedException {
        try {
            Thread.sleep(3000);
            Select branch_code_dropdown = new Select(dropdown);
            branch_code_dropdown.selectByIndex(index);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void selectFeedbackForIGMSPortalEaseOfUse() throws InterruptedException {
//        handleDropdown(feedbackForIGMSPortalEaseOfUse,1);
        feedbackForIGMSPortalEaseOfUse.click();

    }
    public void selectResolutionSatisfactionLevel() throws InterruptedException {
//        handleDropdown(resolutionSatisfactionLevel,1);
        resolutionSatisfactionLevel.click();

    }
    public void downArrow() {
        Actions action = new Actions(driver);

        action.sendKeys(Keys.ARROW_DOWN).build().perform();
    }

    public void enterKeyBoard() {
        Actions action = new Actions(driver);

        action.sendKeys(Keys.ENTER).build().perform();
    }
    public void clickLogout(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", logout);
    }
    public void click_Yes_Confirm_popup() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", update_record_success);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }



    /*Getter Methods*/
    public boolean OTP_Field_visible() {
        try {
            return OTP.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }


    public boolean hasSpecialCharacters(String inputString) {
        // Define a regular expression pattern for special characters
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");

        // Use the matcher method to check for a match
        Matcher matcher = pattern.matcher(inputString);

        // Return true if a special character is found, otherwise false
        return matcher.find();

    }

    public boolean registed_complaint_visibility() {
        try {
            return visible_track_complaint.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }


    public boolean resend_button_visibility() {
        try {
            return Resend.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }


    public boolean view_edit_complaint_visibility() {
        try {
            return View_Edit_Complaints.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }

    public boolean reg_new_complaint_visibility() {
        try {
            return Reg_New_Complaint.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }

    }

    public boolean click_ok_with_return_value() {
        boolean flag = true;
        if(flag){
            click_ok.click();
            return true;
        }else{
            return false;
        }
    }


    public boolean visibility_export() {
        try {
            return click_on_export.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean visibility_all_fields_of_table() {
        try {
            return visibility_table_heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean show_entries() {
        try {
            entries.click();
            entries.sendKeys(Keys.ARROW_DOWN);
            entries.click();
            entries.sendKeys(Keys.ARROW_DOWN);
            entries.sendKeys(Keys.ARROW_DOWN);
            entries.click();
            entries.sendKeys(Keys.ARROW_DOWN);
            entries.sendKeys(Keys.ARROW_DOWN);
            entries.sendKeys(Keys.ARROW_DOWN);
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
        return true;
    }


    public boolean visibility_PH_update_complaint() {

        try {
            return visible_view_edit.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }

    }


    public boolean visibility_uploaded_doc() {

        try {
            return visibility_uploaded_doc.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }

    }


    public String get_complaint_desc() {
        return Complaint_Description.getAttribute("value");
    }

    public String get_complaint_dec_table() {
        return complaint_register.getText();
    }

    public boolean visibilityOfYesBtn() {

        try {
            return selectyes.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }


}
