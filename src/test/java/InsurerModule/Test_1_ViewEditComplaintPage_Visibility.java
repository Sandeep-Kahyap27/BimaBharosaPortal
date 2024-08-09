package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.AdvanceSearchPage;
import ObjectRepository.CallcenterRegPage;
import ObjectRepository.SidebarHeader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_1_ViewEditComplaintPage_Visibility extends MainClass {
    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("Hdfc_Ergo_User_Id"), property.getProperty("Hdfc_Ergo_Password"));
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        Thread.sleep(1000);
        sb.clickcomplaintSidebarTab();
        Thread.sleep(1000);
        sb.clickviewEditcomplaintSidebarTab();
    }
    @Test(priority = 1)
    public void Verify_ViewEditCompalaintPage_Visibility() throws InterruptedException {
        test = extent.createTest("Positive: Verify View Edit Complanint Page Visibility");
        SidebarHeader sb = new SidebarHeader(driver);
        if(sb.ViewEditComplaintsPageVisiblity()){
            test.log(Status.PASS, "View Edit Complanint Page Visibile Successfully.");
            logger.info("Test Pass: View Edit Complanint Page Visibile Successfully.");
        } else {
            test.log(Status.FAIL, "View Edit Complanint Page is not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: View Edit Complanint Page is not Visibile.");
        }
    }

    @Test(priority = 2)
    public void Verify_advanceSearchFilterParameter_visibility() throws InterruptedException {
        test = extent.createTest("Positive: Verify Advance search filter parameter Visibility");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        if(asp.visibilityOfAdvanceSearchFilterParameter()){
            test.log(Status.PASS, "Advance Search Filter parameter Visibile successfully.");
            logger.info("Test Pass: Advance Search Filter parameter Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Advance Search Filter parameter is not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Advance Search Filter parameter is not Visibile.");
        }
    }
    @Test(priority = 3)
    public void Verify_status_type_checkbox_visibility(){
        test = extent.createTest("Positive: Verify Status Type Checkbox Visibility");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        if(asp.visibilityOfStatusTypeCheckbox()){
            test.log(Status.PASS, "Status Type Checkbox Visibile successfully.");
            logger.info("Test Pass: Status Type Checkbox Visibile successfully.");
        } else {
            test.log(Status.FAIL, " Status Type Checkbox is not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Status Type Checkbox is not Visibile.");
        }
    }
    @Test(priority = 4)
    public void Verify_TAT_Type_checkbox_Visibility(){
        test = extent.createTest("Positive: Verify TAT Type Checkbox Visibility");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        if(asp.visibilityOfTATTypeCheckbox()){
            test.log(Status.PASS, "TAT Type Checkbox Visibile successfully.");
            logger.info("Test Pass: TAT Type Checkbox Visibile successfully.");
        } else {
            test.log(Status.FAIL, " TAT Type Checkbox is not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: TAT Type Checkbox is not Visibile.");
        }
    }
    @Test(priority = 5)
    public void Verify_functionality_Clear_button(){
        test = extent.createTest("Positive: Verify functionality of Clear button");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber("test123456");
        asp.clickClearBtn();
        if(asp.ComplaintNumberIsEmpty()){
            test.log(Status.PASS, "Clear button working properly.");
            logger.info("Test Pass: Clear button working properly.");
        } else {
            test.log(Status.FAIL, " Clear button is not working properly.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Clear button is not working properly.");
        }
    }
    @Test(priority = 6)
    public void Verify_SearchComplaintByComplaintNumber(){
        test = extent.createTest("Positive: Verify search complaint by complaint no");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber("test123456");
        asp.clickSearchBtn();
        if(asp.ComplaintNumberIsEmpty()){
            test.log(Status.PASS, "Clear button working properly.");
            logger.info("Test Pass: Clear button working properly.");
        } else {
            test.log(Status.FAIL, " Clear button is not working properly.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Clear button is not working properly.");
        }
    }

    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
