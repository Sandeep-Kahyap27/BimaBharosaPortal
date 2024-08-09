package CallCenterModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallcenterRegPage;
import ObjectRepository.PopUpPage;
import ObjectRepository.SidebarHeader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class Test_CallCenterModule extends MainClass {


    Properties property = null;

    @BeforeClass
    public void launchURL() throws IOException {
        loadURL("LoginURL");
        property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");

    }


    //Author : Abhishek
    @Test(priority = 1)
    public void test_menuBtn_visibility() throws InterruptedException, IOException {

        test = extent.createTest("Positive: Verify Menu button is displayed and clickable on the web page");

        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("CallCenter_UserID"), property.getProperty("CallCenter_Password"));

        SidebarHeader sb = new SidebarHeader(driver);

        if (sb.getMenuBtnStatus()) {
            test.log(Status.PASS, "Menu button is visible and clickable on the web page");
            logger.info("Test Passed : Menu button is visible and clickable on the web page");
        } else {
            test.log(Status.FAIL, "Menu button is either not visible or clickable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Menu button is either not visible or clickable");
        }
    }

    //Author : Abhishek
    @Test(priority = 2, dependsOnMethods = "test_menuBtn_visibility")
    public void test_verify_registration_Against_Entity_By_IRDAI_Page() throws InterruptedException {
        test = extent.createTest("Positive: Verify Registration Against Entity By IRDAI Page");
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        //sb.clickOnComplaints();
        sb.clickOnRegisterAgainstEntity();

        String expectedURL = "https://igmsuat.irdai.gov.in/RegisterComplaint/RegisterComplaint";
        String actualURL = driver.getCurrentUrl();

        String expectedPageTitle = "Complaint Registration | IRDAI";
        String currentPageTitle = driver.getTitle();

        if (actualURL.equals(expectedURL) && currentPageTitle.equals(expectedPageTitle)) {
            test.log(Status.PASS, "User is able to open Registration Against Entity By IRDAI Page");
            logger.info("Test Passed : User is able to open Registration Against Entity By IRDAI Page");
        } else {
            test.log(Status.FAIL, "User is not able to open Registration Against Entity By IRDAI Page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : User is not able to open Registration Against Entity By IRDAI Page");
        }
    }


    //Author : Abhishek
    @Test(priority = 3, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_visibilityOfAllElements_On_RegisterAgainstEntityByIRDAIPage() {
        test = extent.createTest("Positive: Verify visibility of all elements on Registration Against Entity By IRDAI Page");

        CallcenterRegPage cr = new CallcenterRegPage(driver);

        //Verifying Identify By dropdown visibility
        if (cr.verifyStatusOfIdentifyByDropdown()) {
            test.log(Status.PASS, "Identify By drop down is visible on the web page");
            logger.info("Test Passed : Identify By drop down is visible on the web page");
        } else {
            test.log(Status.FAIL, "Identify By drop down is not visible on the web page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Identify By drop down is not visible on the web page");
        }

        //Verifying Mobile Number or Email search input field visibility
        if (cr.verifyStatusOfSearchField()) {
            test.log(Status.PASS, "Mobile number or Email input field is visible on the page");
            logger.info("Test Passed : Mobile number or Email input field is visible on the page");
        } else {
            test.log(Status.FAIL, "Mobile number or Email input field is not visible on the page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Mobile number or Email input field is not visible on the page");
        }

        //Verify search button visibility
        if (cr.verifyStatusOfSearchBtn()) {
            test.log(Status.PASS, "Search button present on the web page");
            logger.info("Test Passed : Search button present on the web page");
        } else {
            test.log(Status.FAIL, "Search button not present on the web page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Search button not present on the web page");
        }

        //Verify pinCode checkbox and field visibility
        if (cr.verifyStatusOfPinCode()) {
            test.log(Status.PASS, "PinCode checkbox is visible and enabled");
            logger.info("Test Passed : PinCode checkbox is visible and enabled");
        } else {
            test.log(Status.FAIL, "PinCode checkbox is not visible and enabled");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : PinCode checkbox is not visible and enabled");
        }

        //Verify complaint nature and classification
        if (cr.verifyComplaintClassficationFields()) {
            test.log(Status.PASS, "All fields related to complaint classification is visible on registration page");
            logger.info("Test Passed : All fields related to complaint classification is visible on registration page");
        } else {
            test.log(Status.FAIL, "All fields related to complaint classification not visible properly");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : All fields related to complaint classification not visible properly");
        }

        //Verify identifier types
        if (cr.verifyIdentifierTypes()) {
            test.log(Status.PASS, "Policy/Proposal Number/Certificate Number/Claim Number radio buttons are displayed");
            logger.info("Test Passed : Policy/Proposal Number/Certificate Number/Claim Number radio buttons are displayed");
        } else {
            test.log(Status.FAIL, "Policy/Proposal Number/Certificate Number/Claim Number radio buttons not displayed");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Policy/Proposal Number/Certificate Number/Claim Number radio buttons not displayed");
        }

        //Verify Policy/Proposal/Certificate/Claim field
        if (cr.verifyPolicyNumberField()) {
            test.log(Status.PASS, "Policy number field is visible and enabled");
            logger.info("Test Passed : Policy number field is visible and enabled");
        } else {
            test.log(Status.FAIL, "Policy number field either not visible or disable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Policy number field either not visible or disable");
        }

        //Verify complaint details field
        if (cr.verifyComplaintDetailsField()) {
            test.log(Status.PASS, "Complaint details field is visible and enabled");
            logger.info("Test Passed : Complaint details field is visible and enabled");
        } else {
            test.log(Status.FAIL, "Complaint details field either not visible or disable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Complaint details field either not visible or disable");
        }

        //Verify register button
       /* if (cr.verifyRegisterButton()) {
            test.log(Status.PASS, "Register button displayed and disabled before entering details");
            logger.info("Test Passed : Register button displayed and disabled before entering details");
        } else {
            test.log(Status.FAIL, "Register button either not displayed or enabled");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Register button either not displayed or enabled");
        }*/
    }


    //Author : Abhishek
    @Test(priority = 4, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_dismiss_back_button_functionality() throws InterruptedException {
        test = extent.createTest("Positive: Verify back button functionality by dismissing alert pop up");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        scrollToComplaint();
        cr.clickOnBack();
        PopUpPage pg = new PopUpPage(driver);
        String expectedConfirmation = "Do you wish to continue?";
        String expectedURL = "https://igmsuat.irdai.gov.in/RegisterComplaint/RegisterComplaint";
        String expectedPageTitle = "Complaint Registration | IRDAI";
        if(pg.verify_dismiss_back_button_functionality().equals(expectedConfirmation) && driver.getTitle().equals(expectedPageTitle) && driver.getCurrentUrl().equals(expectedURL)){
            test.log(Status.PASS, "Back button functionality is working by dismissing alert pop up");
            logger.info("Test Passed : Back button functionality is working by dismissing alert pop up");
        }
        else{
            test.log(Status.FAIL, "Back button functionality not working by dismissing alert pop up!");
            test.fail(new Throwable(),  MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Back button functionality not working by dismissing alert pop up!");
        }
    }


    //Author : Abhishek
    @Test(priority = 5, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_accept_back_button_functionality() throws InterruptedException {
        test = extent.createTest("Positive: Verify back button functionality by accepting alert pop up");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        scrollToComplaint();
        cr.clickOnBack();
        PopUpPage pg = new PopUpPage(driver);
        String expectedConfirmation = "Do you wish to continue?";
        String expectedURL = "https://igmsuat.irdai.gov.in/ViewUpdate/ViewEditComplaint";
        String expectedPageTitle = "View/Edit Complaint | IRDAI";
        if(pg.verify_accept_back_button_functionality().equals(expectedConfirmation) && driver.getTitle().equals(expectedPageTitle) && driver.getCurrentUrl().equals(expectedURL)){
            test.log(Status.PASS, "Back button functionality is working by accepting alert pop up");
            logger.info("Test Passed : Back button functionality is working by accepting alert pop up");
        }
        else{
            test.log(Status.FAIL, "Back button functionality not working by accepting alert pop up!");
            test.fail(new Throwable(),  MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Back button functionality not working by accepting alert pop up!");
        }
    }


    //Author : Abhishek
    @Test(priority = 6, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_IsComplaintRegistered_btn() throws InterruptedException {
        test = extent.createTest("Positive: Verify the radio button for Is Complaint registered is clickable");
        SidebarHeader sb = new SidebarHeader(driver);
        //sb.clickMenu();
        sb.clickOnComplaints();
        sb.clickOnRegisterAgainstEntity();

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        if (cr.verifyIsComplaintRegisteredBtnStatus()) {
            test.log(Status.PASS, "Is Complaint Registered radio buttons are clickable");
            logger.info("Test Passed : Is Complaint Registered radio buttons are clickable");
        } else {
            test.log(Status.FAIL, "Is Complaint Registered radio buttons are not clickable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Is Complaint Registered radio buttons are not clickable");
        }

    }


    //Author : Abhishek
    @Test(priority = 7, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_identifyByDropdown_has_mobileNumber() throws InterruptedException {
        test = extent.createTest("Positive: Verify IdentifyBy Dropdown has mobileNumber option");

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.identifyBy("Mobile Number");

        if (cr.verifyMobileNumberOption()) {
            test.log(Status.PASS, "Mobile Number option present and it's label and field is visible");
            logger.info("Test Passed : Mobile Number option present and it's label and field is visible");
        } else {
            test.log(Status.FAIL, "Mobile Number option not present or it's label and field not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Mobile Number option not present or it's label and field not visible");
        }


    }

    //Author : Abhishek
    @Test(priority = 8, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_searchBtn_intractability() {
        test = extent.createTest("Positive: Verify Search button clickable");
        CallcenterRegPage cr = new CallcenterRegPage(driver);

        if (cr.verifySearchBtnIntractable()) {
            test.log(Status.PASS, "Search button is enabled");
            logger.info("Test Passed : Search button is enabled");
        } else {
            test.log(Status.FAIL, "Search button is disable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Search button is disable");
        }

    }

    //Author : Abhishek
    @Test(priority = 9, dependsOnMethods = "test_identifyByDropdown_has_mobileNumber", enabled = true)
    public void test_search_invalid_MobileNumber() throws InterruptedException {
        test = extent.createTest("Negative: Verify mobile number field with invalid number");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterMobileNumberOrEmail("5654654353");
        cr.search();
        Thread.sleep(3000);

        String warningMsg = "No Data Found";
        if (cr.warningMessage().equals(warningMsg)) {
            test.log(Status.PASS, "Error popUp displayed for invalid mobile number");
            logger.info("Test Passed : Error popUp displayed for invalid mobile number");
        } else {
            test.log(Status.FAIL, "Error popUp not displayed for invalid mobile number");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error popUp not displayed for invalid mobile number");
        }

    }

    //Author : Abhishek
    @Test(priority = 10, dependsOnMethods = "test_identifyByDropdown_has_mobileNumber", enabled = true)
    public void test_empty_mobileNumber_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify mobile number field with empty value");

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.clearMobileNumberOrEmail();
        cr.clickOnRegister();
        Thread.sleep(2000);

        String errorMsg = "Please Enter Number";
        if (cr.errorMessage().equals(errorMsg)) {
            test.log(Status.PASS, "Error message displayed for empty mobile number");
            logger.info("Test Passed : Error message displayed for empty mobile number");
        } else {
            test.log(Status.FAIL, "Error message not displayed for empty mobile number");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for empty mobile number");
        }

    }

    //Author : Abhishek
    @Test(priority = 11, dependsOnMethods = "test_identifyByDropdown_has_mobileNumber", enabled = true)
    public void test_lessThan_10digit_mobileNumber() throws InterruptedException {
        test = extent.createTest("Negative: Verify mobile number field with less than 10 digits");

        random = new Random();

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterMobileNumberOrEmail(String.valueOf(random.nextInt(100)));
        cr.clickOnRegister();
        Thread.sleep(2000);

        String errorMsg = "Please Enter 10 Digit Mobile Number";
        if (cr.errorMessage().equals(errorMsg)) {
            test.log(Status.PASS, "Error message displayed for less than 10 digit mobile number");
            logger.info("Test Passed : Error message displayed for less than 10 digit mobile number");
        } else {
            test.log(Status.FAIL, "Error message not displayed for less than 10 digit mobile number");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for less than 10 digit mobile number");
        }
    }

    //Author : Abhishek
    @Test(priority = 12, dependsOnMethods = "test_identifyByDropdown_has_mobileNumber", enabled = true)
    public void test_search_valid_mobileNumber() throws InterruptedException {
        test = extent.createTest("Positive: Verify mobile number field with valid value");

        String validMobileNumber = "9967465389";
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.clearMobileNumberOrEmail();
        cr.enterMobileNumberOrEmail(validMobileNumber);
        cr.search();
        Thread.sleep(2000);

        if(cr.verifyExistingUserDetails_MobileNumber(validMobileNumber)){
            test.log(Status.PASS, "Existing user details displayed correctly with valid mobile number");
            logger.info("Test Passed : Existing user details displayed correctly with valid mobile number");
        }
        else{
            test.log(Status.FAIL, "Existing user details not displayed or incorrect!!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Existing user details not displayed or incorrect!!");
        }


    }

    //Author : Abhishek
    @Test(priority = 13, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_identifyByDropdown_has_emailID() throws InterruptedException {
        test = extent.createTest("Positive: Verify IdentifyBy Dropdown has emailId option");
        driver.navigate().refresh();
        Thread.sleep(3000);

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.selectIdentifyBy();
        cr.identifyBy("Email Id");

        if (cr.verifyEmailIdOption()) {
            test.log(Status.PASS, "Email ID option present and it's label and field is visible");
            logger.info("Test Passed : Email ID option present and it's label and field is visible");
        } else {
            test.log(Status.FAIL, "Email ID option not present or it's label and field not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Email ID option not present or it's label and field not visible");
        }

    }

    //Author : Abhishek
    @Test(priority = 14, dependsOnMethods = "test_identifyByDropdown_has_emailID", enabled = true)
    public void test_empty_emailId_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify email Id field with empty value");

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.clearMobileNumberOrEmail();
        cr.search();
        Thread.sleep(2000);

        String warningMsg = "Please Enter Search Value";
        if (cr.warningMessage().equals(warningMsg)) {
            test.log(Status.PASS, "Warning pop up displayed when searching with empty emailID field");
            logger.info("Test Passed : Warning pop up displayed when searching with empty emailID field");
        } else {
            test.log(Status.FAIL, "Warning pop up not displayed when searching with empty emailID field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Warning pop up not displayed when searching with empty emailID field");
        }

    }

    //Author : Abhishek
    @Test(priority = 15, dependsOnMethods = "test_identifyByDropdown_has_emailID", enabled = true)
    public void test_search_invalid_emailId() throws InterruptedException {
        test = extent.createTest("Negative: Verify email Id field with invalid email Id");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterMobileNumberOrEmail("abhishek@yahoo.com");
        cr.search();
        Thread.sleep(2000);

        String warningMsg = "No Data Found";
        if (cr.warningMessage().equals(warningMsg)) {
            test.log(Status.PASS, "Warning popUp displayed for invalid email Id");
            logger.info("Test Passed : Warning popUp displayed for invalid email Id");
        } else {
            test.log(Status.FAIL, "Warning popUp not displayed for invalid email Id");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Warning popUp not displayed for invalid email Id");
        }
    }

    //Author : Abhishek
    @Test(priority = 16, dependsOnMethods = "test_identifyByDropdown_has_emailID", enabled = true)
    public void test_search_valid_emailId() throws InterruptedException {
        test = extent.createTest("Positive: Verify email Id field with valid email Id");
        String validEmailId = "ag249713@gmail.com";
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.clearMobileNumberOrEmail();
        cr.enterMobileNumberOrEmail(validEmailId);
        cr.search();
        Thread.sleep(2000);

        if(cr.verifyExistingUserDetails_EmailID(validEmailId)){
            test.log(Status.PASS, "Existing user details displayed correctly with valid email ID");
            logger.info("Test Passed : Existing user details displayed correctly with valid email ID");
        }
        else{
            test.log(Status.FAIL, "Existing user details not displayed or incorrect!!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Existing user details not displayed or incorrect!!");
        }
    }

    //Author : Abhishek
    @Test(priority = 17, dependsOnMethods = "test_search_valid_mobileNumber", enabled = true)
    public void test_verify_pinCode_field() throws InterruptedException {
        test = extent.createTest("Positive: Verify pin Code field");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        //scrollToComplaint();
        if (cr.verifyPinCodeField()) {
            test.log(Status.PASS, "Pin code field is visible");
            logger.info("Test Passed : Pin code field is visible");
        } else {
            test.log(Status.FAIL, "Pin code field is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Pin code field is not visible");
        }

    }

    //Author : Abhishek
    @Test(priority = 18, dependsOnMethods = "test_verify_pinCode_field", enabled = true)
    public void test_verify_pinCode_field_with_invalid_pinCode() throws InterruptedException {
        test = extent.createTest("Negative: Verify pin Code field with invalid pinCode");
        random = new Random();
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterPinCode(String.valueOf(random.nextInt(1000000)));
        cr.clickOnDistrict();

        if(cr.verifyPinCodeField_ForInvalidData()){
            test.log(Status.PASS, "District and state not populated for invalid pinCode");
            logger.info("Test Passed : District and state not populated for invalid pinCode" );
        }
        else{
            test.log(Status.FAIL, "District and state populated for invalid pinCode");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : District and state populated for invalid pinCode");
        }
    }

    //Author : Abhishek
    @Test(priority = 19, dependsOnMethods = "test_verify_pinCode_field", enabled = true)
    public void test_verify_pinCode_field_with_valid_pinCode() throws InterruptedException {
        test = extent.createTest("Positive: Verify pin Code field with invalid pinCode");
        String validPinCode = "400610";
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterPinCode(validPinCode);
        cr.clickOnDistrict();

        if(cr.verifyPinCodeField_ForValidData(validPinCode)){
            test.log(Status.PASS, "District and state auto populated for valid pinCode");
            logger.info("Test Passed : District and state auto populated for valid pinCode" );
        }
        else{
            test.log(Status.FAIL, "District and state not populated for valid pinCode");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : District and state not populated for valid pinCode");
        }
    }

    //Author : Abhishek
    @Test(priority = 20, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_verify_complaintAgainst_dropdown() throws InterruptedException {
        test = extent.createTest("Positive: Verify complaint against dropdown list");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        if(cr.verifyComplaintAgainst_dropDown_intractable()){
            test.log(Status.PASS,"Call center user is able to interact with complaint against dropdown");
            logger.info("Test Passed : Call center user is able to interact with complaint against dropdown");
        }
        else{
            test.log(Status.FAIL, "Call center user unable to interact with complaint against dropdown");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Call center user unable to interact with complaint against dropdown");
        }
    }

    //Author : Abhishek
    @Test(priority = 21, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_verify_mandatory_fields_complaint_against_insCompany() throws InterruptedException {
        test = extent.createTest("Positive: Verify mandatory fields for complaint against Insurance company");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintAgainstType("Company");
        if(cr.verify_fields_against_insuranceCompany()){
            test.log(Status.PASS, "Required fields are displayed for complaint against insurance company");
            logger.info("Test Passed : Required fields are displayed for complaint against insurance company");
        }
        else{
            test.log(Status.FAIL, "All mandatory fields are not visible!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : All mandatory fields are not visible!");
        }

    }

    //Author : Abhishek
    @Test(priority = 22, dependsOnMethods = "test_verify_mandatory_fields_complaint_against_insCompany", enabled = true)
    public void test_verify_complaint_against_select_option() throws InterruptedException {
        test = extent.createTest("Negative: Verify select option for complaint against dropdown");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintAgainstType("Select");
        String comp_against_errmsg = "Please Select Complaint Against";
        if(cr.getComplaintAgainst_errorMsg() != null  &&  cr.getComplaintAgainst_errorMsg().equals(comp_against_errmsg)){
            test.log(Status.PASS, "Expected error message displayed for select option in complaint against dropdown");
            logger.info("Test Passed : Expected error message displayed for select option in complaint against dropdown");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed!!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed!!");
        }
    }

    //Author : Abhishek
    @Test(priority = 23, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_verify_mandatory_fields_complaint_against_unregEntity() throws InterruptedException {
        test = extent.createTest("Positive: Verify required fields for complaint against unregistered entity without insurance company involvement");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintAgainstType("Entity");
        PopUpPage pg = new PopUpPage(driver);
        pg.dismissAlertWindow();
        Thread.sleep(2000);
        if(cr.verify_fields_against_unregEntity_without_insCompany_involvement()){
            test.log(Status.PASS, "Only Required fields are visible for complaint against unregistered entity without involvement of insurance company");
            logger.info("Test Passed : Only Required fields are visible for complaint against unregistered entity without involvement of insurance company");
        }
        else{
            test.log(Status.FAIL, "Required fields are not visible or unexpected fields are present");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Required fields are not visible or unexpected fields are present!");
        }

    }

    //Author : Abhishek
    @Test(priority = 24, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_verify_mandatory_fields_complaint_against_unregEntity_insCompany_involvement() throws InterruptedException {
        test = extent.createTest("Positive: Verify required fields for complaint against unregistered entity with insurance company involvement");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintAgainstType("Entity");
        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(2000);
        if(cr.verify_fields_against_unregEntity_with_insCompany_involvement()){
            test.log(Status.PASS, "Required fields are visible for complaint against unregistered entity with involvement of insurance company");
            logger.info("Test Passed : Required fields are visible for complaint against unregistered entity with involvement of insurance company");
        }
        else{
            test.log(Status.FAIL, "All fields are not visible!!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : All fields are not visible!");
        }

    }

    //Author : Abhishek
    @Test(priority = 25, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_verify_mandatory_fields_complaint_against_intermediary() throws InterruptedException {
        test = extent.createTest("Positive: Verify required fields for complaint against insurance intermediary");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintAgainstType("Intermediary");
        Thread.sleep(2000);
        if(cr.verify_fields_against_insurance_intermediary()){
            test.log(Status.PASS, "Required fields are visible for complaint against insurance intermediary");
            logger.info("Test Passed : Required fields are visible for complaint against insurance intermediary");
        }
        else{
            test.log(Status.FAIL, "All fields are not visible!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : All fields are not visible!");
        }

    }

    //Author : Abhishek
    @Test(priority = 26, dependsOnMethods = "test_verify_mandatory_fields_complaint_against_intermediary", enabled = true)
    public void test_verify_fields_against_broker_intermediary_type() throws InterruptedException {
        test = extent.createTest("Positive: Verify required fields against broker intermediary type");
        String expected_broker_license_number = "B317";
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterIntermediaryType("Broker");
        if(cr.verify_fields_against_broker_intermediary_type()){
            test.log(Status.PASS, "Broker dropdown list and it's license number field is visible");
            logger.info("Test Passed : Broker dropdown list and it's license number field is visible");
        }
        else{
            test.log(Status.FAIL, "Required fields are not visible!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Required fields are not visible!");
        }
        cr.enterBrokerName("A to Z Ins. Brokers Pvt. Ltd.");
        if(cr.verify_broker_license_number(expected_broker_license_number)){
            test.log(Status.PASS, "Broker license number populated correctly");
            logger.info("Test Passed : Broker license number populated correctly");
        }
        else{
            test.log(Status.FAIL, "Broker license number incorrect or not populated");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Broker license number incorrect or not populated");
        }

    }



    //Author : Abhishek
    @Test(priority = 27, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_verify_identifier_type_radiobutton_intractability(){
        test = extent.createTest("Positive: Verify identifier type radio button enabled");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        if(cr.verify_identifier_type_radiobutton()){
            test.log(Status.PASS, "All identifier type radio buttons are enabled");
            logger.info("Test Passed : All identifier type radio buttons are enabled");
        }
        else{
            test.log(Status.FAIL, "All identifier type radio buttons are not enabled");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : All identifier type radio buttons are not enabled");
        }
    }

    //Author : Abhishek
    @Test(priority = 28, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_empty_policy_number_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify policy number field with empty value");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.selectPolicyNumber();
        cr.clickOnPolicyNumber();
        cr.clickOnRegister();
        String policy_number_errormsg = "Please Enter Either Policy / Proposal / Certificate / Claim Number";
        if(cr.getPolicyOrProposalOrCertificateOrClaim_errorMsg().equals(policy_number_errormsg)){
            test.log(Status.PASS, "Error message displayed for empty policy/proposal/certificate/claim number field");
            logger.info("Test Passed : Error message displayed for empty policy/proposal/certificate/claim number field");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed for empty policy number field!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for empty policy number field!");
        }
    }

    //Author : Abhishek
    @Test(priority = 29, dependsOnMethods = "test_empty_policy_number_field", enabled = true)
    public void test_policy_number_length_exceed() throws InterruptedException {
        test = extent.createTest("Negative: Verify policy number field length");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.selectPolicyNumber();
        cr.enter_Policy_Proposal_Certificate_Claim_Number(generateRandomString(100));

        String expectedMsg = "You have reached your maximum limit of characters allowed";
        if(cr.getMaximumLimit_error_handleAlert().equals(expectedMsg)){
            test.log(Status.PASS, "Alert pop up displayed when reaching maximum limit");
            logger.info("Test Passed : Alert pop up displayed when reaching maximum limit");
        }
        else{
            test.log(Status.FAIL, "Alert pop up not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Alert pop up not displayed!");
        }
    }

    //Author : Abhishek
    @Test(priority = 30, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_empty_complaintDetails_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify complaint details field with empty value");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        Thread.sleep(1000);
        //cr.handleAlertPopup("You have reached your maximum limit of characters allowed");
        cr.selectPolicyNumber();
        cr.clearPolicyNumberField();
        cr.enter_Policy_Proposal_Certificate_Claim_Number("PL");
        cr.enterComplaintDetails("");
        cr.clickOnRegister();
        String complaint_details_errorMsg = "Please Enter Complaint Details";
        if(cr.getComplaintDetails_errorMsg().equals(complaint_details_errorMsg)){
            test.log(Status.PASS, "Error message displayed for empty complaint details field");
            logger.info("Test Passed : Error message displayed for empty complaint details field");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed for empty complaint details field!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for empty complaint details field!");
        }

    }

    //Author : Abhishek
    @Test(priority = 31, dependsOnMethods = "test_empty_complaintDetails_field", enabled = true)
    public void test_special_characters_complaintDetails_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify complaint details field with special characters");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintDetails("~!@#$^&;[]");
        cr.clickOnRegister();
        String complaint_details_errorMsg = "Please Enter Valid Complaint Details";
        if(cr.getComplaintDetails_validation_errorMsg().equals(complaint_details_errorMsg)){
            test.log(Status.PASS, "Error message displayed for special characters entered in complaint details field");
            logger.info("Test Passed : Error message displayed for special characters entered in complaint details field");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed for special characters!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed for special characters!");
        }

    }

    //Author : Abhishek
    @Test(priority = 32, dependsOnMethods = "test_special_characters_complaintDetails_field", enabled = true)
    public void test_length_exceed_complaintDetails_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify complaint details field length");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintDetails(generateRandomString(4000));

        String expectedMsg = "You have reached your maximum limit of characters allowed";
        if(cr.getMaximumLimit_error_handleAlert().equals(expectedMsg)){
            test.log(Status.PASS, "Alert pop up displayed when reaching maximum limit");
            logger.info("Test Passed : Alert pop up displayed when reaching maximum limit");
        }
        else{
            test.log(Status.FAIL, "Alert pop up not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Alert pop up not displayed!");
        }

    }

    //Author : Abhishek
    @Test(priority = 33, enabled = true)
    public void test_verify_source_of_complaint() throws InterruptedException {
        test = extent.createTest("Negative: Verify source of complaint dropdown list");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterComplaintDetails("Register complaint on behalf of policy holder");
        cr.clickOnRegister();
        String source_of_complaint_errmsg = "Please Enter Source of complaint";
        if(cr.getSourceOfComplaint_errorMsg().equals(source_of_complaint_errmsg)){
            test.log(Status.PASS, "Error message displayed when source of complaint not selected");
            logger.info("Test Passed : Error message displayed when source of complaint not selected");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed!");
        }
    }

    //Author : Abhishek
    @Test(priority = 34, dependsOnMethods = "test_verify_source_of_complaint", enabled = true)
    public void test_verify_date_of_letter() throws InterruptedException {
        test = extent.createTest("Negative: Verify date of letter dropdown list");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        scrollToUpward();
        cr.enterSourceOfComplaint("Web");
        cr.clickOnRegister();
        String date_of_letter_errmsg = "Please Enter Date of Letter";
        if(cr.getDateOfLetter_errorMsg().equals(date_of_letter_errmsg)){
            test.log(Status.PASS, "Error message displayed when date of letter not selected");
            logger.info("Test Passed : Error message displayed when date of letter not selected");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed!");
        }
    }

    //Author : Abhishek
    @Test(priority = 35, dependsOnMethods = "test_verify_date_of_letter", enabled = true)
    public void test_date_of_letter_invalid_format() throws InterruptedException {
        test = extent.createTest("Negative: Verify date of letter field with invalid date format");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterDateOfLetter("20/06/2024");
        cr.clickOnRegister();
        String invalid_date_of_letter_errmsg = "Invalid Date Format";
        if(cr.getDateOfLetter_errorMsg().equals(invalid_date_of_letter_errmsg)){
            test.log(Status.PASS, "Error message displayed for invalid date format");
            logger.info("Test Passed : Error message displayed for invalid date format");
        }
        else{
            test.log(Status.FAIL, "Invalid date format working!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Invalid date format working!");
        }
    }

    //Author : Abhishek
    @Test(priority = 36, dependsOnMethods = "test_date_of_letter_invalid_format", enabled = true)
    public void test_verify_date_of_receipt_complaint() throws InterruptedException {
        test = extent.createTest("Negative: Verify date of receipt complaint dropdown list");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        //scrollToUpward();
        cr.select_Complaint_Date();
        cr.clickOnRegister();
        String date_of_receipt_errmsg = "Please Enter Date of Receipt of complaint";
        if(cr.getDateOfReceipt_errorMsg().equals(date_of_receipt_errmsg)){
            test.log(Status.PASS, "Error message displayed when date of receipt of complaint not selected");
            logger.info("Test Passed : Error message displayed when date of receipt of complaint not selected");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed!");
        }
    }

    //Author : Abhishek
    @Test(priority = 37, dependsOnMethods = "test_verify_date_of_receipt_complaint", enabled = true)
    public void test_date_of_receipt_invalid_format() throws InterruptedException {
        test = extent.createTest("Negative: Verify date of receipt of complaint field with invalid date format");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterDateOfReceipt("20-06-2024");
        cr.clickOnRegister();
        String invalid_date_of_letter_errmsg = "Invalid Date Format";
        if(cr.getDateOfReceipt_errorMsg().equals(invalid_date_of_letter_errmsg)){
            test.log(Status.PASS, "Error message displayed for date of receipt of complaint with invalid date format");
            logger.info("Test Passed : Error message displayed for date of receipt of complaint with invalid date format");
        }
        else{
            test.log(Status.FAIL, "Invalid date format working!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Invalid date format working!");
        }
    }

    //Author : Abhishek
    @Test(priority = 38, dependsOnMethods = "test_date_of_receipt_invalid_format", enabled = true)
    public void test_verify_file_type() throws InterruptedException {
        test = extent.createTest("Negative: Verify user can upload only PDF,JPG,JPEG and PNG file types");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.select_Complaint_Receipt_Date();
        //scrollToComplaint();
        cr.attachFile(System.getProperty("user.dir")+"/FileAttachment/Bima Bharosa API Automation.pptx");
        cr.upload();
        PopUpPage pg = new PopUpPage(driver);
        String expectedMsg = "Upload only jpeg, pdf, jpg, png files.";
        String file_type_warning_msg = pg.getFileAttachment_warning_msg();
        if(file_type_warning_msg != null && file_type_warning_msg.equals(expectedMsg)){
            test.log(Status.PASS, "Call center user is not able to upload other file formats");
            logger.info("Test Passed : Call center user is not able to upload other file formats");
        }
        else{
            test.log(Status.FAIL, "Other file formats uploaded!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Other file formats uploaded!");
        }
    }

    //Author : Abhishek
    @Test(priority = 39, dependsOnMethods = "test_verify_file_type", enabled = true)
    public void test_verify_file_size() throws InterruptedException {
        test = extent.createTest("Negative: Verify user can not able upload more than 10mb size file");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        //scrollToComplaint();
        cr.attachFile(System.getProperty("user.dir")+"/FileAttachment/BlackMarble_2016.png");
        cr.upload();
        PopUpPage pg = new PopUpPage(driver);
        String expectedMsg = "Total Number of file size should be less than or equal to 10 MB";
        String file_type_warning_msg = pg.getFileAttachment_warning_msg();
        if(file_type_warning_msg != null && file_type_warning_msg.equals(expectedMsg)){
            test.log(Status.PASS, "Call center user is not able to upload more than 10mb size file");
            logger.info("Test Passed : Call center user is not able to upload more than 10mb size file");
        }
        else{
            test.log(Status.FAIL, "More than 10mb size file uploaded!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : More than 10mb size file uploaded!");
        }
    }


    //Author : Abhishek
    @Test(priority = 40, dependsOnMethods = "test_verify_registration_Against_Entity_By_IRDAI_Page", enabled = true)
    public void test_special_character_priority_handling_details_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify priority handling details field with special character");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.selectPriorityHandling("Member of Paliament");
        cr.enterPriorityHandlingDetails("#$%&");
        cr.clickOnRegister();
        String priority_handling_detail_errormsg = "Please Enter Valid Priority Handling Details";
        if(cr.getPriorityHandlingDetails_errorMsg().equals(priority_handling_detail_errormsg)){
            test.log(Status.PASS, "Error message displayed for special characters entered in priority handling details field");
            logger.info("Test Passed : Error message displayed for special characters entered in priority handling details field");
        }
        else{
            test.log(Status.FAIL, "Error message not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed!");
        }
    }

    //Author : Abhishek
    @Test(priority = 41, dependsOnMethods = "test_special_character_priority_handling_details_field", enabled = true)
    public void test_length_exceed_priority_handling_details_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify priority handling details field length");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterPriorityHandlingDetails(generateRandomString(4000));
        String expectedMsg = "You have reached your maximum limit of characters allowed";
        if(cr.getMaximumLimit_error_handleAlert().equals(expectedMsg)){
            test.log(Status.PASS, "Alert pop up displayed when reaching maximum limit");
            logger.info("Test Passed : Alert pop up displayed when reaching maximum limit");
        }
        else{
            test.log(Status.FAIL, "Alert pop up not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Alert pop up not displayed!");
        }
    }

    //Author : Abhishek
    @Test(priority = 42, dependsOnMethods = "test_special_character_priority_handling_details_field", enabled = true)
    public void test_special_characters_remarks_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify remarks field with special characters");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterPriorityHandlingDetails("Register complaint for VIP policy holder");
        cr.enterRemarks("%$#&");
        cr.clickOnRegister();
        String remarks_errormsg = "Please Enter Valid Remarks";
        if (cr.getRemarks_errorMsg().equals(remarks_errormsg)) {
            test.log(Status.PASS, "Error message displayed for special characters entered in remarks field");
            logger.info("Test Passed : Error message displayed for special characters entered in remarks field");
        } else {
            test.log(Status.FAIL, "Error message not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error message not displayed!");
        }

    }


    //Author : Abhishek
    @Test(priority = 43, dependsOnMethods = "test_special_characters_remarks_field", enabled = true)
    public void test_length_exceed_remarks_field() throws InterruptedException {
        test = extent.createTest("Negative: Verify remarks field length");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterRemarks(generateRandomString(4000));
        String expectedMsg = "You have reached your maximum limit of characters allowed";
        if(cr.getMaximumLimit_error_handleAlert().equals(expectedMsg)){
            test.log(Status.PASS, "Alert pop up displayed when reaching maximum limit");
            logger.info("Test Passed : Alert pop up displayed when reaching maximum limit");
        }
        else{
            test.log(Status.FAIL, "Alert pop up not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Alert pop up not displayed!");
        }
    }


    //Author : Abhishek
    @Test(priority = 44, enabled = true)
    public void test_verify_logoff_unsuccess() throws InterruptedException {
        test = extent.createTest("Positive: Verify Call center user not able to log off");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.enterRemarks("Register complaint with remarks");
        scrollToUpward();
        cr.logOutCancel();
        String expectedURL = "https://igmsuat.irdai.gov.in/RegisterComplaint/RegisterComplaint";
        String expectedPageTitle = "Complaint Registration | IRDAI";
        if(driver.getCurrentUrl().equals(expectedURL) && driver.getTitle().equals(expectedPageTitle)){
            test.log(Status.PASS, "Call center user not able to logOff by dismissing alert pop up");
            logger.info("Test Passed : Call center user not able to logOff by dismissing alert pop up");
        }
        else{
            test.log(Status.FAIL, "LogOff functionality not working as expected!");
            test.fail(new Throwable(),  MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : LogOff functionality not working as expected!");
        }
    }


    //Author : Abhishek
    @Test(priority = 45, enabled = true)
    public void test_verify_logoff_successfully() throws InterruptedException {
        test = extent.createTest("Positive: Verify Call center user is able to logOff successfully");
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
        String expectedURL = "https://igmsuat.irdai.gov.in/LoginAdmin/Login";
        String expectedPageTitle = "Login Admin | IRDAI";
        if(driver.getCurrentUrl().equals(expectedURL) && driver.getTitle().equals(expectedPageTitle)){
            test.log(Status.PASS, "Call center user is able to logOff successfully");
            logger.info("Test Passed : Call center user is able to logOff successfully");
        }
        else{
            test.log(Status.FAIL, "LogOff functionality not working as expected!");
            test.fail(new Throwable(),  MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : LogOff functionality not working as expected!");
        }

    }
}
