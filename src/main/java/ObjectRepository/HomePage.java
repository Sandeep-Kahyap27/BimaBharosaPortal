package ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver = null;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement okButton;

    @FindBy(xpath = "(//img[@class='logo-width'])[1]")
    private WebElement bimaBharosa_logo;

    @FindBy(id = "btnRegComplaint")
    private WebElement registerButton;

    @FindBy(id = "txtinput")
    private WebElement mobileNumber;

    @FindBy(xpath = "//*[@id='btnLogin']")
    private WebElement search;

    @FindBy(xpath = "//button[@id='btnotpvalidate']")
    private WebElement OTPSubmitbtn;

    @FindBy(id = "txtotp1")
    private WebElement O1;

    @FindBy(id = "txtotp2")
    private WebElement O2;

    @FindBy(id = "txtotp3")
    private WebElement O3;

    @FindBy(id = "txtotp4")
    private WebElement O4;

    @FindBy(id = "txtotp5")
    private WebElement O5;

    @FindBy(id = "txtotp6")
    private WebElement O6;


    public boolean getOTPsubmitbtn() throws InterruptedException {

        try {
            Thread.sleep(3000);
            return OTPSubmitbtn.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void otp_sendkeys() {
        O1.sendKeys("1");

    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void OkPopup() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", okButton);
    }

    public void registration() {

        try{
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            jse1.executeScript("arguments[0].click();", registerButton);
        }
        catch (NoSuchElementException e){

        }


    }

    public void enterMobileNumber(String mobNumber) throws InterruptedException {

        Thread.sleep(1000);

        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("arguments[0].value = arguments[1];", mobileNumber, mobNumber);
    }

    public String get_spl_char_mob() {

        return mobileNumber.getAttribute("value");
    }

    public void clearField_mob() {

        mobileNumber.clear();
    }

    public boolean otp_pass_chk() {
        try {

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", O2);
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
        return true;
    }

    public void enterOTP_manually_invalid() throws InterruptedException {
        O1.sendKeys("1");
        O2.sendKeys("2");
        O3.sendKeys("3");
        O4.sendKeys("4");
        O5.sendKeys("5");
        O6.sendKeys("6");

        Thread.sleep(3000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", OTPSubmitbtn);

    }

    public void enterOTP_manually_splchar() throws InterruptedException {

        O1.sendKeys("a");
        O2.sendKeys("b");
        O3.sendKeys("c");
        O4.sendKeys("!");
        O5.sendKeys("@");
        O6.sendKeys("#");

        Thread.sleep(3000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", OTPSubmitbtn);

    }


    public boolean OTP_submit_arrow_visible() {
        try {
            return OTPSubmitbtn.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }

    public void trackComplaint() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", search);
    }

    public void OTPFieldClick() {

        O1.click();
    }

    public void enterOTP(String mobileNumber) throws InterruptedException {

        char c[] = mobileNumber.toCharArray();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.elementToBeClickable(O1));
        O1.sendKeys(String.valueOf(c[0]));
        O2.sendKeys(String.valueOf(c[1]));
        O3.sendKeys(String.valueOf(c[2]));
        O4.sendKeys(String.valueOf(c[3]));
        O5.sendKeys(String.valueOf(c[4]));
        O6.sendKeys(String.valueOf(c[5]));
        Thread.sleep(3000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", OTPSubmitbtn);

    }

    public void clearOTP() throws InterruptedException {
        for (int i = 0; i <= 5; i++) {
            WebElement OTP = driver.findElement(By.xpath("(//input[@class='text-center numberinput inputs'])[" + (i + 1) + "]"));
            OTP.sendKeys("9");
            Thread.sleep(1000);

        }

        for (int i = 5; i >= 0; i--) {
            WebElement OTP = driver.findElement(By.xpath("(//input[@class='text-center numberinput inputs'])[" + (i + 1) + "]"));
            OTP.click();
            OTP.clear();
            Thread.sleep(1000);
        }
    }

    public void InvalidOTPValidationPage(String c) throws InterruptedException {
        try {
            for (int i = 0; i <= 5; i++) {
                WebElement OTP = driver.findElement(By.xpath("(//input[@class='text-center numberinput inputs'])[" + (i + 1) + "]"));
                OTP.sendKeys(c);
                Thread.sleep(1000);
            }
            clickValidatebutton();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }


    }


    public void clickValidatebutton() {
        try {
            OTPSubmitbtn.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }

    }

    public void clearOTPfield() throws InterruptedException {

        for (int i = 5; i >= 0; i--) {
            WebElement OTP = driver.findElement(By.xpath("(//input[@class='text-center numberinput inputs'])[" + (i + 1) + "]"));
            OTP.click();
            OTP.clear();
            Thread.sleep(1000);
        }
    }


    //Getter methods
    public boolean getBimaBharosaPortalLogo() {

        boolean status = false;

        try {
            status = bimaBharosa_logo.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element Not Found!!");
        }


        return status;
    }


    public boolean getRegisterButtonVisibility() {

        boolean visible = false;

        try {
            visible = registerButton.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        return visible;
    }

    public boolean getRegisterButtonInteraction() {

        boolean enable = false;

        try {
            enable = registerButton.isEnabled();
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }

        return enable;
    }

}
