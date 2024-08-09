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

public class Test_2_SearchComplaintByStatus extends MainClass {

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
    public void Verify_SearchComplaintByNewStatus(){
        test = extent.createTest("Positive: Verify Search Complaint By New Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.clickNewStatus();
        asp.clickSearchBtn();
        if(asp.getDataFromSearchTable("New")){
            test.log(Status.PASS, " Searched New status complaint Visibile successfully.");
            logger.info("Test Pass: Searched New status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched New status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched New status complaint is not Visibile.");
        }
    }
    @Test(priority = 2)
    public void Verify_SearchComplaintByAcknowledgeStatus() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By Acknowledged Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickAcknowledgeStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getDataFromSearchTable("Acknowledged")){
            test.log(Status.PASS, " Searched Acknowledged status complaint Visibile successfully.");
            logger.info("Test Pass: Searched Acknowledged status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched Acknowledged status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched Acknowledged status complaint is not Visibile.");
        }
    }
    @Test(priority = 3)
    public void Verify_SearchComplaintByPendingStatus() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By Pending Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickPendingStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getDataFromSearchTable("Pending")){
            test.log(Status.PASS, " Searched Pending status complaint Visibile successfully.");
            logger.info("Test Pass: Searched Pending status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched Pending status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched Pending status complaint is not Visibile.");
        }
    }
    @Test (priority = 4)
    public void Verify_SearchComplaintByAttendedToStatus() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By AttendedTo Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickAttendedToStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getDataFromSearchTable("Attended to")){

            test.log(Status.PASS, " Searched AttendedTo status complaint Visibile successfully.");
            logger.info("Test Pass: Searched AttendedTo status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched AttendedTo status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched AttendedTo status complaint is not Visibile.");
        }
    }
    @Test(priority = 5)
    public void Verify_SearchComplaintByEscalatedStatus() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By Escalated Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickEscalatedStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getDataFromSearchTable("Escalated")){

            test.log(Status.PASS, " Searched Escalated status complaint Visibile successfully.");
            logger.info("Test Pass: Searched Escalated status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched Escalated status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched Escalated status complaint is not Visibile.");
        }
    }
    @Test(priority = 6)
    public void Verify_SearchComplaintByReOpenStatus() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By Re-open Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickUncheckEscalatedStatus();
        asp.clickReopenStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getDataFromSearchTable("Re-open")){

            test.log(Status.PASS, " Searched Re-open status complaint Visibile successfully.");
            logger.info("Test Pass: Searched Re-open status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched Re-open status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched Re-open status complaint is not Visibile.");
        }
    }
    @Test(priority = 7)
    public void Verify_SearchComplaintByClosedStatus() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By Closed Status");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickUncheckEscalatedStatus();
        asp.clickUncheckReopenStatus();
        asp.clickClosedStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getDataFromSearchTable("Closed")){

            test.log(Status.PASS, " Searched Closed status complaint Visibile successfully.");
            logger.info("Test Pass: Searched Closed status complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched Closed status complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched Closed status complaint is not Visibile.");
        }
    }

    @Test(priority = 8)
    public void Verify_SearchComplaintByTATBetween_15_to_30_days() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By TAT Between 15 to 30 days");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickUncheckEscalatedStatus();
        asp.clickUncheckReopenStatus();
        asp.clickUncheckClosedStatus();
        asp.clickTatGreaterThan15();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getTATDataFromSearchTable(15,30)){

            test.log(Status.PASS, " Searched TAT between 15 to 30 days complaint Visibile successfully.");
            logger.info("Test Pass: Searched TAT between 15 to 30 days complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched TAT between 15 to 30 days complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched TAT between 15 to 30 days complaint is not Visibile.");
        }
    }

    @Test(priority = 9)
    public void Verify_SearchComplaintByTATBetween_31_to_60_days() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By TAT Between 31 to 60 days");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickUncheckEscalatedStatus();
        asp.clickUncheckReopenStatus();
        asp.clickUncheckClosedStatus();
        asp.clickUncheckTatGreaterThan15();
        asp.clickTatGreaterThan30();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        if(asp.getTATDataFromSearchTable(31,60)){

            test.log(Status.PASS, " Searched TAT between 31 to 60 days complaint Visibile successfully.");
            logger.info("Test Pass: Searched TAT between 31 to 60 days complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched TAT between 31 to 60 days complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched TAT between 31 to 60 days complaint is not Visibile.");
        }
    }

    @Test(priority = 10)
    public void Verify_SearchComplaintByTAT_GreaterThanTwoMonth() throws InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By TAT Greater Than Two Month");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickUncheckEscalatedStatus();
        asp.clickUncheckReopenStatus();
        asp.clickUncheckClosedStatus();
        asp.clickUncheckTatGreaterThan15();
        asp.clickUncheckTatGreaterThan30();
        asp.clickTatGreaterThan60();
        asp.clickSearchBtn();

        Thread.sleep(4000);
        if(asp.getTATDataFromSearchTable(61, 100)){

            test.log(Status.PASS, " Searched TAT Greater Than Two Month complaint Visibile successfully.");
            logger.info("Test Pass: Searched TAT Greater Than Two Month complaint Visibile successfully.");
        } else {
            test.log(Status.FAIL, "Searched TAT Greater Than Two Month complaint not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched TAT Greater Than Two Month complaint is not Visibile.");
        }
    }

    @Test(priority = 11)
    public void Verify_ViewEditIconClickble() throws InterruptedException {
        test = extent.createTest("Positive: Verify View Edit Icon is visible");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckClosedStatus();

        Thread.sleep(4000);
        if(asp.visibilityOfViewEditIcon()){

            test.log(Status.PASS, " View Edit icon is Visibile successfully.");
            logger.info("Test Pass: View Edit icon is Visibile successfully.");
        } else {
            test.log(Status.FAIL, "View Edit icon is not Visibile.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: View Edit icon is not Visibile.");
        }

    }
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
