package ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ComplaintRegPage {

    WebDriver driver;

    @FindBy(id = "txtname_individual")
    private WebElement name;

    @FindBy(id = "txtmob_individual")
    private WebElement mobileNumber;

    @FindBy(id = "txtmob_individual-error")
    private WebElement txtmoberror;

    @FindBy(id = "txtotp1")
    private WebElement otpemptyfield;

    @FindBy(id = "lnkForgotPass")
    private WebElement resendotpgettxt;

    @FindBy(id = "warningMessage")
    private WebElement otpwarningMessage;

    @FindBy(id = "lblOTP")
    private WebElement PleaseEnteroneTimeOtpgettxt;

    @FindBy (xpath = "//small[text()='Please Enter Six Digit Pin-Code']")
    private WebElement emptypinCodeError;

    @FindBy(xpath = "//small[text()='Please Enter Valid Pin-Code']")
    private WebElement invalidPinCodeError;

    @FindBy (xpath = "//a[text()=' Home ']")
    private WebElement home;

    @FindBy (id ="btnwarningModal")
    private WebElement okbutton;

    @FindBy(id = "txtemailofcompl_individual")
    private WebElement email;

    @FindBy(xpath = "(//*[contains(text(),'E-mail of')])[1]")
    private WebElement emailText;

    @FindBy(id = "txtPinCode")
    private WebElement pinCode;

    @FindBy(id = "txtDistrict")
    private WebElement district;

    @FindBy(id = "txtState")
    private WebElement state;

    @FindBy(xpath = "//div[@id='ddlcomplagainstinsurer_chosen']")
    private WebElement complaint_against_dropdown;

    @FindBy(xpath = "//div[@id='ddlcomplagainstinsurer_chosen']//input[@type='text']")
    private WebElement complaint_against_inputField;

    @FindBy(xpath = "//div[@id='ddlinsurancecomp_chosen']")
    private WebElement insCompanyName_dropdown;

    @FindBy(xpath = "//div[@id='ddlinsurancecomp_chosen']//input[@type='text']")
    private WebElement insCompName_nameField;

    @FindBy(xpath = "//div[@id='ddlpolicytype_chosen']")
    private WebElement policyType_dropdown;

    @FindBy(xpath = "//div[@id='ddlpolicytype_chosen']//input[@type='text']")
    private WebElement policyType_Field;

    @FindBy(xpath = "//div[@id='ddlcomplainttype_chosen']")
    private WebElement complaintType_dropdown;

    //Insurance company fields
    @FindBy(xpath = "//div[@id='ddlcomplainttype_chosen']//input[@type='text']")
    private WebElement complaint_type_field;

    @FindBy(xpath = "//div[@id='ddlcomplaintdesctype_chosen']")
    private WebElement complaintDescType_dropdown;

    @FindBy(xpath = "//div[@id='ddlcomplaintdesctype_chosen']//input[@type='text']")
    private WebElement complaint_desctype_field;

    @FindBy(id = "rdPolicyNumber")
    private WebElement policyNumberRdBtn;

    @FindBy(xpath = "//input[@id='txtPolicyProposalCertifiateClaimNumber']")
    private WebElement policyNumber;

    @FindBy(xpath = "//textarea[@id='txtcompldetails']")
    private WebElement complaintDescription;

    @FindBy(id = "btnsave")
    private WebElement register;

    @FindBy(id = "btncancel")
    private WebElement back;

    @FindBy(id = "btnConfirmSuccess")
    private WebElement btnConfirmSuccess;

    @FindBy(id = "btnConfirmCancel")
    private WebElement btnConfirmCancel;

    @FindBy(id = "Acknowladgepopupclose")
    private WebElement ackPopUp;

    @FindBy(xpath = "//p[text()='Complaint Registered Successfully.']")
    private WebElement registrationMsg;

    @FindBy(id = "warningMessage")
    private WebElement warningMessage;

    //Unregistered Entity fields
    @FindBy(id = "txtcompname_unregcomp")
    private WebElement unRegCompName;

    @FindBy(id = "txtadd_unregcomp")
    private WebElement unRegCompAddress;

    @FindBy(id = "txtphone_unregcomp")
    private WebElement unRegCompPhNumber;

    @FindBy(id = "txtdesc_unregcomp")
    private WebElement unRegCompDesc;

    @FindBy(id = "txttypeofserv_unregcomp")
    private WebElement unRegCompTypeSvcObtained;

    @FindBy(id = "txtcontpername_unregcomp")
    private WebElement unRegCompContactPersonName;

    //Insurance Intermediary fields
    @FindBy(xpath = "//div[@id='ddlintermediarytype_chosen']")
    private WebElement insIntermediaryType;

    @FindBy(xpath = "//div[@id='ddlintermediarytype_chosen']//input[@type='text']")
    private WebElement insIntermediaryType_inputField;

    @FindBy(xpath = "//div[@id='ddlbrokername_chosen']")
    private WebElement brokerName_dropdown;

    @FindBy(xpath = "//div[@id='ddlbrokername_chosen']//input[@type='text']")
    private WebElement brokerName_inputField;

    @FindBy(xpath = "//button[@id='btnwarningModal']")
    private WebElement duplicateWarningError;

    Actions action;


    public ComplaintRegPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void enterName(String name) {

        try{
            this.name.sendKeys(name);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void enterMobileNumber(String mobileNumber) {

        try{
            this.mobileNumber.sendKeys(mobileNumber);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void mobileNumClk(){
        try{
            mobileNumber.click();
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void invalidmobileNumSendkeys(String invalid){
        mobileNumber.sendKeys(invalid);
    }

    public void mobNumClear(){

        mobileNumber.clear();
    }

    public void homeclk(){
        home.click();
    }

    public void okbuttonclk(){
        okbutton.click();
    }

    public void enterEmail(String email) {
        this.email.click();

    }

    public void enterPinCode(String pinCode) throws InterruptedException {

        Thread.sleep(1000);

        this.pinCode.sendKeys(pinCode);
        action = new Actions(driver);
        action.keyDown(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();

    }

    public void districClick(){

        district.click();
    }

    public void pinCodeClick(){

        pinCode.click();
    }

    public void scrollToComplaint() throws InterruptedException {

        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(3000);

    }


    public void complaint_Against(String compAgainst) throws InterruptedException {
        try{
            action = new Actions(driver);
            complaint_against_dropdown.click();

            Thread.sleep(1000);

            complaint_against_inputField.sendKeys(compAgainst);
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }


    }


    public void intermediary_Type(String type) throws InterruptedException {
        try{
            action = new Actions(driver);
            insIntermediaryType.click();

            Thread.sleep(1000);

            insIntermediaryType_inputField.sendKeys(type);
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }


    }

    public void insCompanyName(String insCompanyName) throws InterruptedException {

        try{
            action = new Actions(driver);
            insCompanyName_dropdown.click();

            Thread.sleep(1000);

            insCompName_nameField.sendKeys("HDFC Ergo General Insurance Company Ltd");
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(3000);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }


    }


    public void policyType(String policyType) throws InterruptedException {

        try{
            action = new Actions(driver);
            policyType_dropdown.click();

            Thread.sleep(1000);

            policyType_Field.sendKeys(policyType);
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }


    }

    public  void complaintType(String compType) throws InterruptedException {

        try{
            action = new Actions(driver);
            complaintType_dropdown.click();

            Thread.sleep(1000);

            complaint_type_field.sendKeys(compType);
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }

    }

    public void complaintDescType(String compDescType) throws InterruptedException {

        try{
            action = new Actions(driver);
            complaintDescType_dropdown.click();

            Thread.sleep(1000);

            complaint_desctype_field.sendKeys(compDescType);
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }


    }

    public void brokerName(String brokerName) throws InterruptedException {

        try{
            action = new Actions(driver);
            brokerName_dropdown.click();

            Thread.sleep(1000);

            brokerName_inputField.sendKeys(brokerName);
            action.sendKeys(Keys.ENTER).perform();

            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch(ElementNotInteractableException e){
            e.printStackTrace();
        }


    }

    public void policyNumberClick() {

        try{
            policyNumberRdBtn.click();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
        }
    }

    public void enterPolicyNumber(String policyNumber) throws InterruptedException {
        if(!policyNumber.contains("PL")){
            try{
                this.policyNumber.sendKeys(policyNumber);
                Thread.sleep(2000);
            }
            catch (NoSuchElementException e){
                e.printStackTrace();
            }
            catch (ElementNotInteractableException e)
            {
                e.printStackTrace();
            }
        }
        else {
            try{
                Random random = new Random();
                this.policyNumber.sendKeys(policyNumber+random.nextInt(100000000));
                Thread.sleep(2000);
            }
            catch (NoSuchElementException e){
                e.printStackTrace();
            }
            catch (ElementNotInteractableException e)
            {
                e.printStackTrace();
            }
        }

    }

    public void enterCompDesc(String complaintDesc) throws InterruptedException {
        try{
            complaintDescription.sendKeys(complaintDesc);
            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
        }

    }

    public void registerClick() throws InterruptedException {
        try{
            register.click();
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
        }

    }


    public void backClick() {
        try{
            back.click();
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
        }
    }

    public void acceptAlertWindow() throws InterruptedException {

        try{
            Thread.sleep(3000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnConfirmSuccess);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
        }

    }

    public void dismissAlertWindow() throws InterruptedException {

        try{
            Thread.sleep(3000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnConfirmCancel);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
        }

    }

    public boolean registerComplaint() throws InterruptedException {
        Thread.sleep(3000);

        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(ackPopUp));
            ackPopUp.click();
            return true;
        }
        catch (Exception e){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(duplicateWarningError));
            duplicateWarningError.click();
            return false;
        }

    }

    public String getMessage() {
        if (ackPopUp.isDisplayed())
        {
            return registrationMsg.getText();
        }
        else if (duplicateWarningError.isDisplayed())
        {
            return warningMessage.getText();
        }
        else{
            return  null;
        }
    }

    public void emailText() {

        emailText.click();
    }

    public void enterUnRegCompanyName(String unRegCompName) throws InterruptedException {
        try{
            this.unRegCompName.sendKeys(unRegCompName);
            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void enterUnRegCompanyAddress(String unRegCompAddress) throws InterruptedException {
        try{
            this.unRegCompAddress.sendKeys(unRegCompAddress);
            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void enterUnRegCompanyPhoneNumber(String unRegCompPhNumber) throws InterruptedException {
        try{
            this.unRegCompPhNumber.sendKeys(unRegCompPhNumber);
            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void enterUnRegCompanyDesc(String unRegCompDesc) throws InterruptedException {
        try{
            this.unRegCompDesc.sendKeys(unRegCompDesc);
            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }


    public void enterTypesOfSvcObtained(String unRegCompTypeSvcObtained) throws InterruptedException {
        try{
            this.unRegCompTypeSvcObtained.sendKeys(unRegCompTypeSvcObtained);
            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }


    public void enterContactPersonName(String unRegCompContactPersonName) throws InterruptedException {
        try{
            this.unRegCompContactPersonName.sendKeys(unRegCompContactPersonName);
            Thread.sleep(2000);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }


    public String txtmoberrorm() {

        return txtmoberror.getText();
    }

    public boolean mobDisableField() {
        return mobileNumber.isDisplayed();

    }


    public boolean resendOTPbtn() {

        return resendotpgettxt.isDisplayed();
    }


    public void resendotpClick() {

        resendotpgettxt.click();
    }

    public boolean otpwarningMg() {

        return otpwarningMessage.isDisplayed();
    }

    public void explicitWaitForResendbtn(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(resendotpgettxt));

    }


    public boolean getResendOTPbtnStatus(){

        return PleaseEnteroneTimeOtpgettxt.isEnabled();
    }


    public String emptyPinCodeErrorText(){
        System.out.println(emptypinCodeError.getText());
        return emptypinCodeError.getText();

    }

    public String invalidPinCodeErrorText(){
        System.out.println(invalidPinCodeError.getText());
        return invalidPinCodeError.getText();

    }


    public void invalidpinCdsendkeys(){

        pinCode.sendKeys("111222");
    }

    public void pincodeclear(){

        pinCode.clear();
    }


    //Getters
    public boolean getDistrictStatus() {

        boolean status = false;
        try{
            district.getAttribute("aria-invalid");
            status = true;
            return status;
        }
        catch (Exception e){
            return status;
        }
    }

    public boolean getStateStatus() {

        boolean status = false;
        try{
            state.getAttribute("aria-invalid");
            status = true;
            return status;
        }
        catch (Exception e){
            return status;
        }
    }

    public boolean getMobFieldStatus() {

        return mobileNumber.isEnabled();
    }


}
