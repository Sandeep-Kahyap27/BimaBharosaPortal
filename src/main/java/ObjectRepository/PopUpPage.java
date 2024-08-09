package ObjectRepository;

import Utilities.WaitTimeHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopUpPage {

    WebDriver driver = null;
    Actions actions;
    @FindBy(xpath = "//button[@id='btnConfirmSuccess']")
    @CacheLookup
    private WebElement acceptAlertWindow;


    @FindBy(id = "Acknowladgepopupclose")
    @CacheLookup
    private WebElement ackPopUp;

    @FindBy(xpath = "//p[text()='Complaint Registered Successfully.']")
    @CacheLookup
    private WebElement registrationMsg;

    @FindBy(id = "lblTokenNumber")
    @CacheLookup
    private WebElement complaintNumber;

    @FindBy(xpath = "//button[@id='btnwarningModal']")
    @CacheLookup
    private WebElement warningBtn;

    @FindBy(id = "warningMessage")
    @CacheLookup
    private WebElement warningMessage;

    @FindBy(id = "successMessage")
    @CacheLookup
    private WebElement fileUpload_msg;

    @FindBy(xpath = "//button[@id='btnsuccessModal']")
    @CacheLookup
    private WebElement fileUpload_success_btn;

    @FindBy(id = "ConfirmationMessage")
    @CacheLookup
    private WebElement confirmation_message;

    @FindBy(xpath = "//button[@id='btnConfirmCancel']")
    @CacheLookup
    private WebElement dismissAlertWindow;

    @FindBy(id = "btnsuccessModal")
    @CacheLookup
    private WebElement complaint_updated_success_btn;

    @FindBy(xpath = "//h4[@id='successMessage']")
    @CacheLookup
    private WebElement complaint_updated_success_msg;


    public PopUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*Action Methods*/
    public void acceptAlertWindow() throws InterruptedException {

        try {
            Thread.sleep(2000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", acceptAlertWindow);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }

    }

    public void dismissAlertWindow() throws InterruptedException {

        try {
            Thread.sleep(3000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", dismissAlertWindow);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }

    }

    /*Getter Methods*/
    public String getMessage() {
        if (ackPopUp.isDisplayed()) {
            String msg = registrationMsg.getText();
            System.out.println("Complaint Number :" + complaintNumber.getText());
            ackPopUp.click();
            return msg;
        } else if (warningMessage.isDisplayed()) {
            String msg = warningMessage.getText();
            warningBtn.click();
            return msg;
        } else {
            return null;
        }

    }

    public String getErrorPopupMsg(){
        if (warningMessage.isDisplayed()) {
            String msg = warningMessage.getText();
            warningBtn.click();
            return msg;
        }
        else{
            return null;
        }
    }

    public String getComplaintNumber() {
        if (ackPopUp.isDisplayed()) {
            String complaint_number = complaintNumber.getText();
            ackPopUp.click();
            return complaint_number;
        } else {
            return null;

        }
    }

    public String getWarningMessage() {
        if (warningMessage.isDisplayed()) {
            String warning_msg = warningMessage.getText();
            warningBtn.click();
            return warning_msg;
        } else {
            return null;
        }
    }

    public String getFileUpload_successMsg() throws InterruptedException {
        if (fileUpload_msg.isDisplayed()) {
            String message = fileUpload_msg.getText();
            Thread.sleep(1000);
            fileUpload_success_btn.click();
            return message;
        } else {
            return null;
        }
    }

    public String getFileAttachment_warning_msg() throws InterruptedException {
        if (warningMessage.isDisplayed()) {
            String warning_msg = warningMessage.getText();
            Thread.sleep(1000);
            warningBtn.click();
            return warning_msg;
        } else {
            return null;
        }
    }

    public String verify_dismiss_back_button_functionality() throws InterruptedException {
        if (confirmation_message.isDisplayed()) {
            String confirm = confirmation_message.getText();
            dismissAlertWindow();
            return confirm;
        } else {
            return null;
        }
    }

    public String verify_accept_back_button_functionality() throws InterruptedException {
        if (confirmation_message.isDisplayed()) {
            String confirm = confirmation_message.getText();
            System.out.println(confirm);
            acceptAlertWindow();
            return confirm;
        } else {
            return null;
        }
    }

    public String getUpdateConfirmationMessage() {
        try {
            if (complaint_updated_success_msg.isDisplayed()) {
                String msg = complaint_updated_success_msg.getText();
                complaint_updated_success_btn.click();
                return msg;
            }
        } catch (NoSuchElementException e) {

        }
        return null;
    }


}
