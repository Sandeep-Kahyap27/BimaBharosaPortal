package ComplaintRegistrationModule;

import Base.MainClass;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import Utilities.DataReader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;


public class Test_ComplaintRegistrationModule extends MainClass {

    @BeforeClass
    public void launchURL(){
        loadURL("Url");
    }


    @Test(priority = 1)
    public void test_verifyHomePageURL() throws IOException {

        test = extent.createTest("Positive: Verify home page URL is as expected");
        Properties prop = Property.readPropertiesFile(filePath);
        String expectedURL = prop.getProperty("Url");


        String currentURL = driver.getCurrentUrl();

        if (currentURL.equals(expectedURL)) {
            test.log(Status.PASS, "Home Page URL is as expected");
            logger.info("Test Passed : Home Page URL is as expected");
        } else {
            test.log(Status.FAIL, "Home Page URL is incorrect");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Home Page URL is incorrect");
        }

    }

    @Test(priority = 2)
    public void test_registration_btn_functionlity() {

        test = extent.createTest("Positive: Verify registration button is visible and clickable");
        HomePage h = new HomePage(driver);

        if (h.getRegisterButtonVisibility()) {
            test.log(Status.PASS, "Register Complaint button is visible");
            logger.info("Test Passed : Register Complaint button is visible");

        } else {
            test.log(Status.FAIL, "Register Complaint button is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Register Complaint button is not visible");
        }

        if (h.getRegisterButtonInteraction()) {
            test.log(Status.PASS, "Register Complaint button is clickable");
            logger.info("Test Passed : Register Complaint button is clickable");
        } else {
            test.log(Status.FAIL, "Register Complaint button is not clickable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Register Complaint button is not clickable");
        }

    }

    @Test(priority = 3)
    public void test_verifyCompRegPageURL() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify complaint registration page URL is as expected");
        HomePage h = new HomePage(driver);
        h.registration();

        //Adding 3 seconds of wait to load registration page
        Thread.sleep(3000);

        Properties prop = Property.readPropertiesFile(filePath);
        String CompRegPageURL = prop.getProperty("RegPageUrl");

        String ActualUrl = driver.getCurrentUrl();

        if (ActualUrl.equals(CompRegPageURL)) {
            test.log(Status.PASS, "Registration page URL is correct");
            logger.info("Test Passed : Registration page URL is correct");

        } else {
            test.log(Status.FAIL, "Registration page URL is incorrect");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Registration page URL is incorrect");
        }

    }

    @Test(priority = 4)
    public void test_verifyCompRegPageTitle() throws IOException {

        test = extent.createTest("Positive: Verify complaint registration page title");
        String CompRegPageTitle = "Register Complaint | IRDAI";

        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);

        if (actualTitle.equals(CompRegPageTitle)) {
            test.log(Status.PASS, "Registration page title is correct");
            logger.info("Test Passed : Registration page title is correct");
        } else {
            test.log(Status.FAIL, "Registration page title is incorrect");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Registration page title is incorrect");
        }

    }

    @Test(dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class, priority = 5)
    public void test_empty_Mobile_Number_negative(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) {

        test = extent.createTest("Negative: Verify mobile number field with empty");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.enterName(name);
        crg.mobileNumClk();
        crg.emailText();

        String errormsg = "Please Enter 10 Digit Mobile Number";
        if ((crg.txtmoberrorm()).equals(errormsg)) {
            test.log(Status.PASS, "Mobile field validation without entering any number passed!");
            logger.info("Test Passed : Mobile field validation without entering any number passed!");
        } else {
            test.log(Status.FAIL, "Mobile field validation without entering any number failed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Mobile field validation without entering any number failed!");
        }


    }

    @Test(priority = 6)
    public void test_invalid_Mobile_Number_negative() throws InterruptedException {

        test = extent.createTest("Negative: Verify mobile number field with invalid mobile number");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.invalidmobileNumSendkeys("12345");

        Thread.sleep(3000);

        crg.emailText();

        String errormsg = "Please Enter 10 Digit Mobile Number";
        if (crg.txtmoberrorm().equalsIgnoreCase(errormsg)) {
            test.log(Status.PASS, "Mobile field validation with invalid any number passed!");
            logger.info("Test Passed : Mobile field validation with invalid any number passed!");
        } else {
            test.log(Status.FAIL, "Mobile field validation with invalid  any number failed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Mobile field validation with invalid  any number failed!");
        }

    }

    @Test(priority = 7, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_length_Mobile_Number(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException {

        test = extent.createTest("Negative: Verify mobile number length");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.mobNumClear();
        crg.enterMobileNumber(mob);

        Thread.sleep(3000);

        int mobnumlength = mob.length();

        // Validate the length of the mobile number
        if (mobnumlength == 10) {
            test.log(Status.PASS, "Mobile number length is valid.");
            logger.info("Test Passed : Mobile number length is valid.");
        } else {

            test.log(Status.FAIL, "Mobile number length is not valid.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Mobile number length is not valid.");
        }


    }


    @Test(priority = 8, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_valid_Mobile_Number(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException {

        test = extent.createTest("Positive: Verify mobile number field with valid mobile number");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.enterMobileNumber(mob);
        crg.emailText();

        //Adding wait of 3 seconds to available OTP window
        Thread.sleep(3000);

        hm.enterOTP(mob);

        if (crg.getMobFieldStatus()) {
            test.log(Status.FAIL, "Failed to enter mobile number correctly");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Failed to enter mobile number correctly");
        } else {
            test.log(Status.PASS, "Mobile number entered successfully");
            logger.info("Test Passed : Mobile number entered successfully");
        }

    }

    // OTP Validation

     @Test(priority = 9, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
     public void test_OTPValidation_Initial_Display(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException {
        test = extent.createTest("Positive: Verify initial display of OTP window and validate button");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);

        Thread.sleep(1000);

        crg.homeclk();
        hm.OkPopup();
        hm.registration();
        crg.enterName(name);
        crg.enterMobileNumber(mob);
        crg.emailText();

        if (hm.getOTPsubmitbtn()) {
            test.log(Status.PASS, "Initial_Display, Validate button is displayed");
            logger.info("Test Passed : Initial_Display, Validate button is displayed");
        } else {
            test.log(Status.FAIL, "Initial_Display, Validate button is not displayed");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Initial_Display, Validate button is not displayed");
        }

        Thread.sleep(3000);
    }


    @Test(priority = 10)
    public void test_emptyOTP_validation_negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify OTP field with empty");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);

        hm.clickValidatebutton();

        if (crg.otpwarningMg()) {
            test.log(Status.PASS, "Error message displayed for empty OTP");
            logger.info("Test Passed : Error message displayed for empty OTP");
        } else {
            test.log(Status.FAIL, "Error message not displayed for empty OTP");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for empty OTP");
        }

        Thread.sleep(3000);
    }


    @Test(priority = 11)
    public void test_otpValidationTest_With_Alphabetic_negative() throws InterruptedException {

        test = extent.createTest("Negative: Verify OTP field with alphabetic character");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.okbuttonclk();
        hm.InvalidOTPValidationPage("A");

        if (hm.getOTPsubmitbtn()) {
            test.log(Status.PASS, "Alphabetic characters is not considered as OTP");
            logger.info("Test Passed : Alphabetic characters is not considered as OTP");
        } else {
            test.log(Status.FAIL, "Alphabetic characters it is considering as OTP");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Alphabetic characters it is considering as OTP");
        }

        Thread.sleep(3000);

    }

    @Test(priority = 12)
    public void test_otpValidationTest_Special_Char_negative() throws InterruptedException {

        test = extent.createTest("Negative: Verify OTP field with special character");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        hm.OTPFieldClick();
        hm.InvalidOTPValidationPage("$");

        if (hm.getOTPsubmitbtn()) {
            test.log(Status.PASS, "Special characters are not considered as OTP");
            logger.info("Test Passed : Special characters are not considered as OTP");
        } else {
            test.log(Status.FAIL, "Special characters are considered as OTP");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Special characters are considered as OTP");
        }
        Thread.sleep(3000);
    }


    @Test(priority = 13)
    public void test_InvalidOTPValidation_negative() throws InterruptedException {

        test = extent.createTest("Negative: Verify OTP field with invalid OTP");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        hm.OTPFieldClick();
        hm.InvalidOTPValidationPage("4");

        if (hm.getOTPsubmitbtn()) {
            test.log(Status.PASS, "Invalid OTP not considered");
            logger.info("Test Passed : Invalid OTP not considered");
        } else {
            test.log(Status.FAIL, "Invalid OTP considered");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Invalid OTP considered");
        }

        Thread.sleep(3000);
    }


    @Test(priority = 14)
    public void test_TimeoutOTPValidation() throws InterruptedException {

        test = extent.createTest("Positive: Verify resend OTP button is visible after 60s");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.explicitWaitForResendbtn();

        if (crg.resendOTPbtn()) {
            test.log(Status.PASS, "Resend OTP button is visible after 60s");
            logger.info("Test Passed : Resend OTP button is visible after 60s");
        } else {
            test.log(Status.FAIL, "Resend OTP button is not visible after 60s");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Resend OTP button is visible after 60s");
        }

        Thread.sleep(3000);
    }

    @Test(priority = 15)
    public void test_ResendOTPButton_Validation() throws InterruptedException {

        test = extent.createTest("Positive: Verify resend OTP button is enabled");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.resendotpClick();

        if (crg.getResendOTPbtnStatus()) {
            test.log(Status.PASS, "Resend OTP button is enabled");
            logger.info("Test Passed : Resend OTP button is enabled");
        } else {
            test.log(Status.FAIL, "Resend OTP button is disable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Resend OTP button is disable");
        }

        Thread.sleep(3000);
    }

    @Test(priority = 16, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_OTPValidation_Valid(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException {

        test = extent.createTest("Positive: Verify OTP field with valid OTP");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        hm.OTPFieldClick();
        hm.enterOTP(mob);

        if (crg.mobDisableField()) {
            test.log(Status.PASS, "Valid OTP validation successful!");
            logger.info("Test Passed : Valid OTP validation successful!");
        } else {
            test.log(Status.FAIL, "Valid OTP validation failed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Valid OTP validation failed!");
        }

        Thread.sleep(3000);

    }

    //PINCODE Validation

    @Test(priority = 17)
    public void test_emptyPincodeValidation_negative() throws InterruptedException {

        test = extent.createTest("Negative: Verify pinCode field with empty data");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.scrollToComplaint();
        crg.pinCodeClick();
        String pinCodeErr = "Please Enter Six Digit Pin-Code";
        crg.districClick();

        if (crg.emptyPinCodeErrorText().contains(pinCodeErr)) {
            test.log(Status.PASS, "Error message displayed for empty pin code field.");
            logger.info("Test Passed : Error message displayed for empty pin code field.");

        } else {
            test.log(Status.FAIL, "Error message not displayed for empty pin code field.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for empty pin code field.");
        }

    }


    @Test(priority = 18)
    public void test_InvalidPincodeTest_negative() throws InterruptedException {

        test = extent.createTest("Negative: Verify pinCode field with invalid pinCode");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);

        crg.invalidpinCdsendkeys();
        crg.districClick();

        String pinCodeErr = "Please Enter Valid Pin-Code";

        if (crg.invalidPinCodeErrorText().contains(pinCodeErr)) {
            test.log(Status.PASS, "Error message displayed for invalid PIN code.");
            logger.info("Test Passed : Error message displayed for invalid PIN code.");

        } else {
            test.log(Status.FAIL, "Error message not displayed for invalid PIN code");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for invalid PIN code");
        }

    }


    @Test(priority = 19, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_pincodeLengthValidation(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException {

        test = extent.createTest("Negative: Verify pinCode length is as expected");
        HomePage hm = new HomePage(driver);

        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.pincodeclear();
        crg.enterPinCode(pinCode);

        if (pinCode.length() == 6) {
            test.log(Status.PASS, "Pin code length is correct");
            logger.info("Test Passed : Pin code length is correct");
        } else {
            test.log(Status.FAIL, "Pin code length is Incorrect");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Pin code length is Incorrect");
        }
    }


    @Test(priority = 20, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_valid_PincodeFieldValidation(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException {

        test = extent.createTest("Positive: Verify pinCode field with valid pinCode");
        HomePage hm = new HomePage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.enterPinCode(pinCode);
        crg.districClick();

        if(crg.getDistrictStatus()){
            test.log(Status.PASS, "District populated based on valid pinCode");
            logger.info("Test Passed : District populated based on valid pinCode");
        }
        else{
            test.log(Status.FAIL, "District not populated");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : District not populated");
        }
        if(crg.getStateStatus()){
            test.log(Status.PASS, "State populated based on valid pinCode");
            logger.info("Test Passed : State populated based on valid pinCode");
        }
        else{
            test.log(Status.FAIL, "State not populated");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : State not populated");
        }

        Thread.sleep(3000);

    }

}