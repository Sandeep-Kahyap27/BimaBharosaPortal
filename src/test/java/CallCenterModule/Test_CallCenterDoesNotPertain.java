package CallCenterModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallCenterDoesNotPertainPage;
import ObjectRepository.CallcenterViewEditPage;
import ObjectRepository.LoginPage;
import ObjectRepository.SidebarHeader;
import Utilities.DataReader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_CallCenterDoesNotPertain extends MainClass {

    Properties pro = null;

    @BeforeClass
    public void launchUrl() {
        loadURL("LoginURL");
    }


    @Test(priority = 1)
    public void test_Navigate_Does_Not_Pertain_Page() throws InterruptedException, IOException{
        test = extent.createTest("Positive: Verify we able to navigate Does Not Pertain Page");
        LoginHelper.backToSafety();
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));
        ve.clkMenuButton();
        ve.clkComplaintDropDown();
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        dnp.clkDoesNotPertain();

        if (dnp.displayDoesNotPertain()) {
            test.log(Status.PASS, "Test Pass : Navigation to the Does Not Pertain page.");
            logger.info("Test Passed : Navigation to the Does Not Pertain page is successful");
        } else {
            test.log(Status.FAIL, "Test Fail : Navigation to the Does Not Pertain page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error(" Test Failed : Navigation to the Does Not Pertain page");
        }


    }

    @Test(priority = 2)
    public void test_Default_Table_Entity() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Default Table Entity on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        dnp.selectDefaultTableEntity();

        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        if (dnp.displayOneTo10TableEntity()) {
            test.log(Status.PASS, "Test Pass : The table contain the default data");
            logger.info("Test Passed : Test Pass : The table contain the default data");
        } else {
            test.log(Status.FAIL, "Test Fail : The table does not contain the expected data");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table does not contain the expected value");

        }

    }

    @Test(priority = 3)
    public void test_Specific_Table_Entity() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Specific Table Entity on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        dnp.selectSpecificTableEntity();

        if (dnp.displayoneTo50TableEntity()) {
            test.log(Status.PASS, "Test Pass : The table contain the Specific data");
            logger.info("Test Passed : The table contain the Specific data");
        } else {
            test.log(Status.FAIL, "Test Fail : The table does not contain the Specific data");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : The table does not contain the Specific data");

        }

        dnp.selectDefaultTableEntity();
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.scrollToDownWord();
        ve.scrollToDownWord();
        ve.scrollToDownWord();
        ve.scrollToDownWord();
    }

    @Test(priority = 4)
    public void test_Next_Button_Table() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Next Button Table on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        dnp.clkNext();
        if (dnp.displayElevenTo20TableEntity()) {

            test.log(Status.PASS, "Test Pass : Next Button is Clickable ");
            logger.info("Test Passed : Next Button is Clickable ");
        } else {
            test.log(Status.FAIL, "Test Fail : Next Button is not Clickable ");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Next Button is not Clickable");


        }
    }

    @Test(priority = 5)
    public void test_Previous_Button_Table() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Previous Button Table on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        dnp.clkPrevious();
        if (dnp.displayOneTo10TableEntity()) {

            test.log(Status.PASS, "Test Pass : Previous Button is Clickable");
            logger.info("Test Passed : Previous Button is Clickable");
        } else {
            test.log(Status.FAIL, "Test Fail : Previous Button is not Clickable ");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Previous Button is not Clickable");


        }
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.scrollToUpWord();
        ve.scrollToUpWord();
        ve.scrollToUpWord();
        ve.scrollToUpWord();
    }

    @Test(priority = 6)
    public void test_Edit_Buttons() throws Exception {
        test = extent.createTest("Positive: Verify the Edit button on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        dnp.clkEditBtn();
        if (dnp.displayUpdateInsurer()) {

            test.log(Status.PASS, "Test Pass : Edit Icon is Clickable and We able to navigate Update Insurer Page");
            logger.info("Test Passed : Edit Icon is Clickable and We able to navigate Update Insurer Page");
        } else {

            test.log(Status.FAIL, "Test Fail : Edit Icon is not Clickable and We are not able to navigate Update Insurer Page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Edit Icon is not Clickable and We are not able to navigate Update Insurer Page");
        }

    }

    @Test(priority = 7)
    public void test_Update_Buttons() throws Exception {
        test = extent.createTest("Positive: Verify the Update button on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        ve.scrollToDownWord();
        ve.scrollToDownWord();
        ve.scrollToDownWord();

        if (dnp.updateButtonClickable()) {
            test.log(Status.PASS, "Test Pass : Update is Clickable");
            logger.info("Test Passed : Update is Clickable");
        } else {

            test.log(Status.FAIL, "Test Fail : Update is not Clickable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Update is not Clickable");
        }

    }

    @Test(priority = 7)
    public void test_Back_Buttons() throws Exception {
        test = extent.createTest("Positive: Verify the Back button on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);

        if (dnp.backButtonClickable()) {
            test.log(Status.PASS, "Test Pass : Back is Clickable");
            logger.info("Test Passed : Back is Clickable");
        } else {

            test.log(Status.FAIL, "Test Fail : Back is not Clickable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Back is not Clickable");
        }

    }

    @Test(priority = 8)
    public void test_Update_Insurer() throws Exception {
        test = extent.createTest("Positive: Verify We able to Update Insurer Company on Does Not Pertain Page");
        CallCenterDoesNotPertainPage dnp = new CallCenterDoesNotPertainPage(driver);
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        LoginPage lp = new LoginPage(driver);
        ve.clkMenuButton();
        ve.clkComplaintDropDown();
        dnp.clkDoesNotPertain();
        dnp.clkEditBtn();
        String comNumIDToken = dnp.getTextComplaintNumIDToken();
        String befroeMdifyInsCom = dnp.getAttributeSelectedInsCompany();
        ve.scrollToDownWord();
        dnp.clkNameInsurancCompany();
        lp.UseDownArrowKey();
        Thread.sleep(1000);
        lp.UseDownArrowKey();
        lp.UseEnterKey();
        dnp.clkUpdateBtn();
        dnp.clkYesButton();
        String expectedSuccessfullyPopUp = "Record Update Successfully";
        String actualSuccessfullyPopUp = dnp.getTextSuccessfullyPopUp();
        dnp.clkOkBtn();
        Thread.sleep(5000);
        SidebarHeader sd = new SidebarHeader(driver);
        sd.clickMenu();
        sd.clickOnComplaints();
        ve.clkViewEditDropDown();
        ve.enterComplaintNumber(comNumIDToken);
        ve.clkSearchBtn();
        ve.scrollToDownWord();
        String expectmodifyInsCompanyName = dnp.getTextModifyInsCompanyName();
        if (actualSuccessfullyPopUp.equals(expectedSuccessfullyPopUp)) {
            if (!befroeMdifyInsCom.equals(expectmodifyInsCompanyName)) {
                test.log(Status.PASS, "Test Pass : We are able to update the Insurance Company");
                logger.info("Test Passed : We are able to update the Insurance Company");
            }

        } else {
            test.log(Status.FAIL, "Test Fail : We are not able to update the Insurance Company");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : We are not able to update the Insurance Company");
        }

    }
}