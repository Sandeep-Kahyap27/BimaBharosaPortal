package ObjectRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    static WebDriver driver = null;
    static Actions action;
    @FindBy(id = "txtusername")
    @CacheLookup
    private WebElement username;

    @FindBy(id = "txtpassword")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "btnLogin")
    @CacheLookup
    private WebElement loginbtn;

    @FindBy(id = "btnclear")
    @CacheLookup
    private WebElement clrbtn;

    @FindBy(id = "captchacode")
    @CacheLookup
    private WebElement cptcha;

    @FindBy(xpath = "//img[@id='m_imgCaptcha']")
    @CacheLookup
    private WebElement captchaText;

    @FindBy(id = "imgRefreshCaptcha")
    @CacheLookup
    private WebElement refreshctcha;

    @FindBy(id = "langDropdown_chosen")
    @CacheLookup
    private WebElement languageDropdown;

    @FindBy(xpath = "//label[@id='txtusername-error']")
    @CacheLookup
    private WebElement errorUsername;

    @FindBy(xpath = "//label[@id='txtpassword-error']")
    @CacheLookup
    private WebElement errorPassword;

    @FindBy(xpath = "//*[@id='warningMessage']")
    @CacheLookup
    private WebElement errorInvalidCredential;

    @FindBy(xpath = "//*[@id='warningMessage']")
    @CacheLookup
    private WebElement errInvalidCaptcha;

    @FindBy(xpath = "//*[@id='captchacode-error']")
    @CacheLookup
    private WebElement errorCaptcha;

    @FindBy(xpath = "//label[@id='label_English']")
    @CacheLookup
    private WebElement englishVerify;

    @FindBy(xpath = "//label[@id='label_Hindi']")
    @CacheLookup
    private WebElement hindiVerify;

    @FindBy(xpath = "//button[@id='btnwarningModal']")
    @CacheLookup
    private WebElement okPOPUP;

    @FindBy(xpath = "(//*[contains(text(),'View / Edit')])[2]")
    @CacheLookup
    private WebElement ViewEdit;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String usrname) {

        username.sendKeys(usrname);
    }

    public void clrUsername() {
        username.clear();
    }

    public void clrPassword() {
        password.clear();
    }

    public void enterPassword(String pwd) {

        password.sendKeys(pwd);
    }

    public void clkLoginbtn() {

        loginbtn.click();
    }

    public void clkClrbtn() {
        clrbtn.click();
    }

    public void enterCaptcha(String captcha) {
        cptcha.sendKeys(captcha);
    }

    public void clrCaptcha() {
        cptcha.clear();
    }

    public void clkRefreshCaptcha() {

        refreshctcha.click();
    }

    public void clkLanguageDropdown() {
        languageDropdown.click();
    }

    public boolean displayLoginBtn() {

        loginbtn.isDisplayed();

        return loginbtn.isDisplayed();

    }

    public boolean displayClearBtn() {
        clrbtn.isDisplayed();

        return clrbtn.isDisplayed();
    }

    public boolean displayCaptcha() {
        captchaText.isDisplayed();
        return captchaText.isDisplayed();
    }

    public String attributCaptch() {
        captchaText.getAttribute("src");
        return captchaText.getAttribute("src");
    }

    public boolean atrributUsername() {
        String userPassAttribute = username.getAttribute("value");
        if (userPassAttribute.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public String getTextErrUsername() {
        errorUsername.getText();
        return errorUsername.getText();
    }

    public String getTextErrPassword() {
        errorPassword.getText();
        return errorPassword.getText();
    }

    public String getTextErrInvalidCredential() {
        errorInvalidCredential.getText();
        return errorInvalidCredential.getText();
    }

    public String getTextErrCaptcha() {

        errorCaptcha.getText();
        return errorCaptcha.getText();
    }

    public String getTextErrCaptchaPopUp() {
        errInvalidCaptcha.getText();
        return errInvalidCaptcha.getText();
    }

    public String getTextEnglishLanguage() {
        englishVerify.getText();
        return englishVerify.getText();

    }

    public String getTextHindiLanguage() {
        hindiVerify.getText();
        return hindiVerify.getText();

    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clkOK() {
        okPOPUP.click();
    }

    public void UseDownArrowKey() {
        action = new Actions(driver);
        action.keyDown(Keys.ARROW_DOWN).build().perform();
    }

    public void UseUpArrowKey() {
        action = new Actions(driver);
        action.keyUp(Keys.ARROW_UP).build().perform();
    }

    public void UseEnterKey() {
        action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
    }

    public String getTextViewEdit() {
        ViewEdit.getText();
        return ViewEdit.getText();
    }

}
