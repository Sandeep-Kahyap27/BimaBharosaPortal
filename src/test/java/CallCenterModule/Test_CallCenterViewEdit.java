package CallCenterModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallcenterRegPage;
import ObjectRepository.CallcenterViewEditPage;
import ObjectRepository.LoginPage;
import Utilities.DataReader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_CallCenterViewEdit extends MainClass {

    Properties pro = null;

    @BeforeClass
    public void launchUrl() {
        loadURL("LoginURL");
    }

    @Test(priority = 1)
    public void test_Navigate_View_Edit_Complaints_Page() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify we able to navigate View Edit complaints Page");
        LoginHelper.backToSafety();
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        LoginPage lp = new LoginPage(driver);
        lp.clrUsername();
        lp.clrPassword();
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));
        ve.clkMenuButton();
        ve.clkComplaintDropDown();
        ve.clkViewEditDropDown();
        String expectedViewEdit = "View / Edit Complaints";
        String actualViewEdit = lp.getTextViewEdit();

        if (actualViewEdit.equals(expectedViewEdit)) {

            test.log(Status.PASS, "Test Pass : Navigation to the View/Edit page is successful");
            logger.info("Test Passed : Navigation to the View/Edit page is successful");
        } else {
            test.log(Status.FAIL, "Test Fail : Navigation to the View/Edit page is unsuccessful");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error(" Test Failed : Navigation to the View/Edit page is unsuccessful");
        }


    }

    // @Test(priority = 2)
    public void text_Clear_Button_Functionality_ViewEditPage() throws InterruptedException, IOException {
        test = extent.createTest("Verify the Clear button functionality on ViewEditPage");
        LoginHelper.backToSafety();
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        Thread.sleep(5000);
        ve.enterPolicyNumber("1234566");
        Thread.sleep(5000);
        ve.clkClearBtn();
        if (ve.getAttributePolicyNumber()) {
            test.log(Status.PASS, "Test Pass : Clear button is visible and clickable on View/Edit Page");
            logger.info("Test Pass : Clear button is visible and clickable on View/Edit Page");
        } else {
            test.log(Status.FAIL, "Test Fail : Clear buttons is not visible and clickable View/Edit Page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail : Clear buttons is not visible and clickable View/Edit Page");
        }

    }

    @Test(priority = 3)
    public void text_Visibility_Buttons_ViewEditPage() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify the buttons are visible and clickable on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        boolean search = ve.displaySearchButton();
        boolean clear = ve.displayClearButton();
        ve.scrollToDownWord();
        ve.clkNextButton();
        ve.scrollToDownWord();


        if (search && clear && ve.displayExportToExcel() && ve.displayFirstButton() && ve.displayNextButton()
                && ve.displayLastButton() && ve.displayPreviousButton()) {
            test.log(Status.PASS, "Test Pass : All buttons are visible and clickable on View/Edit Page");
            logger.info("Test Passed : All buttons are visible and clickable on View/Edit Page");
        } else {

            test.log(Status.FAIL, "Test Fail : All buttons are not visible and clickable View/Edit Page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : All buttons are not visible and clickable View/Edit Page");
        }

        ve.scrollToUpWord();
        ve.scrollToUpWord();
    }

    @Test(priority = 4)
    public void text_Search_Buttons_Empty_ViewEditPage() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify the buttons are visible and clickable on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.clkSearchBtn();
        String actualErrSearchMsg = "Enter Atleast One Search Criteria";
        if (ve.getTextErrEmptySearch().equals(actualErrSearchMsg)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed when searching without data");
            logger.info("Test Passed : Correct error messages displayed when searching without data");
        } else {

            test.log(Status.FAIL, "Test Fail : Error messages not displayed when searching without data");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed when searching without data");
        }

        ve.clkOk();
    }

    @Test(priority = 5)
    public void test_Search_Buttons_InValid_ComplaintNumber() throws InterruptedException {
        test = extent.createTest("Negative: Verify the Search button with Invalid Complaint Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterComplaintNumber("ABC12345");
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();

        String extpectShowingEntity = "Showing 0 to 0 of 0 entries";
        if (ve.getTextShowingEntityZero().equals(extpectShowingEntity)) {
            test.log(Status.PASS, "Test Passed : The table shows zero entities with Invalid Complaint Number .");
            logger.info("Test Passed : The table shows zero entities with Invalid Complaint Number .");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows entities with Invalid Complaint Number.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows entities with Invalid Complaint Number.");
        }


        ve.scrollToUpWord();
        ve.clrComplaintNumber();


    }

    @Test(priority = 6, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Search_Buttons_Valid_ComplaintNumber(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Search button with valid Complaint Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterComplaintNumber(ComplaintNumber);
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();
        Thread.sleep(1000);

        String expectValidComplaintNum = "06-24-000615";
        if (ve.getTextValidComplaintNumber().equals(expectValidComplaintNum)) {

            test.log(Status.PASS, "Test Passed : The table shows Valid Complaint Number entity.");
            logger.info("Test Passed : The table shows Valid Complaint Number entity.");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows zero entities with Valid Complaint Number entity.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows zero entities with Valid Complaint Number entity.");
        }

        ve.scrollToUpWord();
        ve.clrComplaintNumber();

    }

    @Test(priority = 7)
    public void test_Search_Buttons_InValid_EntityReferenceNumber() throws InterruptedException {
        test = extent.createTest("Negative: Verify the Search button with Invalid Entity Reference Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterEntityReferenceNumber("ABC12345");
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();

        String extepectShowingEntity = "Showing 0 to 0 of 0 entries";
        if (ve.getTextShowingEntityZero().equals(extepectShowingEntity)) {
            test.log(Status.PASS, "Test Passed : The table shows zero entities with Invalid Entity Reference Number.");
            logger.info("Test Passed : The table shows zero entities with Invalid Entity Reference Number.");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows entities with Invalid Entity Reference Number.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows entities with Invalid Entity Reference Number.");
        }

        ve.scrollToUpWord();
        ve.clrEntityReferenceNumber();


    }

    @Test(priority = 8, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Search_Buttons_Valid_EntityReferenceNumber(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Search button with valid Entity Reference Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterEntityReferenceNumber(EntityReferenceNumber);
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();
        Thread.sleep(1000);

        String expectValidEntityReferenceNumber = "HDFCG005915";
        if (ve.getTextValidEntityReferenceNumber().equals(expectValidEntityReferenceNumber)) {

            test.log(Status.PASS, "Test Passed : The table shows entity Valid Entity Reference Number .");
            logger.info("Test Passed : The table shows entity Valid Entity Reference Number .");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows zero entity with Valid Entity Reference Number .");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows zero entity with Valid Entity Reference Number .");
        }

        ve.scrollToUpWord();
        ve.clrEntityReferenceNumber();
    }


    @Test(priority = 9)
    public void test_Search_Buttons_InValid_PolicyNumber() throws InterruptedException {
        test = extent.createTest("Negative: Verify the Search button with Invalid Policy Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterPolicyNumber("Sand@123");
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();

        String expectShowingEntity = "Showing 0 to 0 of 0 entries";
        if (ve.getTextShowingEntityZero().equals(expectShowingEntity)) {
            test.log(Status.PASS, "Test Passed : The table shows zero entities with Invalid Policy Number.");
            logger.info("Test Passed : The table shows zero entities with Invalid Policy Number.");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows entities with Invalid Entity Reference Number.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows entities with Invalid Policy Number.");
        }

        ve.scrollToUpWord();
        ve.clrPolicyNumber();


    }

    @Test(priority = 10, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Search_Buttons_Valid_PolicyNumber(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Search button with valid Policy Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterPolicyNumber(PolicyNumber);
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();
        Thread.sleep(1000);

        String expectValidPolicyNumber = "121231";
        if (ve.getTextValidPolicyNumber().equals(expectValidPolicyNumber)) {

            test.log(Status.PASS, "Test Passed : The table shows entity Valid Policy Number .");
            logger.info("Test Passed : The table shows entity Valid Policy Number .");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows zero entity with Valid Policy Number.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows zero entity with Valid Policy Number .");
        }

        ve.scrollToUpWord();
        ve.clrPolicyNumber();
    }


    @Test(priority = 11)
    public void test_Search_Buttons_InValid_ComplainantName() throws InterruptedException {
        test = extent.createTest("Negative: Verify the Search button with Invalid Complainant Name on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterComplainantName("123ABC");
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();

        String expectShowingEntity = "Showing 0 to 0 of 0 entries";
        if (ve.getTextShowingEntityZero().equals(expectShowingEntity)) {
            test.log(Status.PASS, "Test Passed : The table shows zero entities with Invalid Complainant Name.");
            logger.info("Test Passed : The table shows zero entities with Invalid Complainant Name.");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows entities with Invalid Complainant Name.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows entities with Invalid Complainant Name.");
        }

        ve.scrollToUpWord();
        ve.clrComplainantName();


    }

    @Test(priority = 12, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Search_Buttons_Valid_ComplainantName(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Search button with valid Complainant Name on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterComplainantName(ComplainantName);
        ve.clkSearchBtn();
        ve.scrollToDownWord();
        Thread.sleep(1000);

        String expectValidComplaintName = "MR SANDEEP KASHYAP";
        if (ve.getTextValidComplainantName().equals(expectValidComplaintName)) {

            test.log(Status.PASS, "Test Passed : The table shows entity Valid Complainant Name.");
            logger.info("Test Passed : The table shows entity Valid Complainant Name .");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows zero entity with Valid Complainant Name.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows zero entity with Valid Complainant Name.");
        }

        ve.scrollToUpWord();
        ve.clrComplainantName();
    }

    @Test(priority = 13)
    public void test_Search_Buttons_InValid_MobileNumber() throws InterruptedException {
        test = extent.createTest("Negative: Verify the Search button with Invalid Mobile Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterMobileNumberViewEditPage("123ABC");
        ve.clkSearchBtn();
        Thread.sleep(1000);
        ve.scrollToDownWord();

        String expectShowingEntity = "Showing 0 to 0 of 0 entries";
        if (ve.getTextShowingEntityZero().equals(expectShowingEntity)) {
            test.log(Status.PASS, "Test Passed : The table shows zero entities with Invalid Mobile Number");
            logger.info("Test Passed : The table shows zero entities with Invalid Mobile Number");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows entities with Invalid Mobile Number");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows entities with Invalid Mobile Number");
        }

        ve.scrollToUpWord();
        ve.clrMobileNum();


    }

    @Test(priority = 14, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Search_Buttons_Valid_MobileNumber(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Search button with valid Mobile Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterMobileNumberViewEditPage(MobileNumber);
        ve.clkSearchBtn();
        ve.scrollToDownWord();
        Thread.sleep(1000);

        String expectMobileNum = "Showing 1 to 10 of 0 entries (Total Page: 0)";
        if (ve.getTextValidMobileNumOfTable().equals(expectMobileNum)) {

            test.log(Status.PASS, "Test Passed : The table shows entity Valid Mobile Number ");
            logger.info("Test Passed : The table shows entity Valid Mobile Number");
        } else {

            test.log(Status.FAIL, "Test Failed : The table shows zero entity with Valid Mobile Number");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table shows zero entity with Valid Mobile Number");
        }

        ve.scrollToUpWord();
        ve.clrMobileNum();
    }

    @Test(priority = 15, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Edit_Buttons_Valid_MobileNumber(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Edit button with valid Mobile Number on ViewEditPage");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.enterMobileNumberViewEditPage(MobileNumber);
        ve.clkSearchBtn();
        ve.scrollToDownWord();
        Thread.sleep(1000);
        ve.clkEditIcon();

        if (ve.displayUpdateComplaints()) {

            test.log(Status.PASS, "Test Pass : Edit Icon is Clickable and We able to navigate Update Complaint Page");
            logger.info("Test Passed : Edit Icon is Clickable and We able to navigate Update Complaint Page");
        } else {

            test.log(Status.FAIL, "Test Fail : Edit Icon is not Clickable and We are not able to navigate Update Complaint Page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Edit Icon is not Clickable and We are not able to navigate Update Complaint Page");
        }

    }

    @Test(priority = 16, dataProvider = "Call_Center_Login", dataProviderClass = DataReader.class)
    public void test_Cancel_Buttons_Functionality(String ComplaintNumber, String EntityReferenceNumber, String PolicyNumber, String ComplainantName, String MobileNumber) throws Exception {
        test = extent.createTest("Positive: Verify the Cancel button Functionality on Update Complaint Page");
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.scrollToDownWord();
        ve.scrollToDownWord();
        ve.scrollToDownWord();

        if (ve.displayCancelBtn()) {
            ve.clkCancelBtn();

            if (ve.displayViewEdit()) {
                test.log(Status.PASS, "Test Pass : Cancel Button is Clickable and We able to navigate View/Edit Page");
                logger.info("Test Passed : Cancel Button is Clickable and We able to navigate View/Edit Page");
            }

        } else {

            test.log(Status.FAIL, "Test Fail : Cancel Button is not Clickable and We are not able to navigate View/Edit Page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Cancel Button is not Clickable and We are not able to navigate View/Edit Page");
        }

    }

    @AfterClass
    public void logOut() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }

}