package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.*;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_19_ClosureRequestTo_IRDAI extends MainClass {
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

    @Test(priority = 78)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_without_dateOfPayment_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto without Date of Payment field.");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickUncheckEscalatedStatus();
        asp.clickReopenStatus();
        asp.clickSearchBtn();
        Thread.sleep(2000);
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Attended to");
        crg.scrollToComplaint();
        vcd.enterFinalResolutionRemarks("Test Data");
        vcd.enterFinalResolutionRemarkDateField();
        vcd.click_iF_final_resolution_letter_button();
        vcd.click_upload_attachment_field("C:\\Users\\manishab\\IdeaProjects\\BimaBharosaPortal\\src\\main\\resources\\TestData\\dummy-pdf_2.pdf");
        vcd.click_Upload_Button();
        vcd.clickToOkButtonConfirmUpdate();
        vcd.clickOnClosureRequestToIRDAI_checkbox();
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getDateOfPayment_errorMsg();
        String expected_errorMsg = "Please Enter Date Of Payment";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent without Date of Payment field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent without Date of Payment field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent without Date of Payment field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent without Date of Payment field. having complaint no - " +complaintNo);
        }
    }
    @Test(priority = 79)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_with_Invalid_dateOfPayment_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto with Invalid Date of Payment field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("Jan-30-2024");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getDateOfPayment_errorMsg();
        String expected_errorMsg = "Invalid Date Format";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent with Invalid Date of Payment field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent with Invalid Date of Payment field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent with Invalid Date of Payment field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent with Invalid Date of Payment field. having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 80)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_without_paymentChequeDate_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto without Payment Cheque Date field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getPaymentChequeDate_errorMsg();
        String expected_errorMsg = "Please Enter Payment Cheque Date";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent without Payment Cheque Date field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent without Payment Cheque Date field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent without Payment Cheque Date field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent without Payment Cheque Date field. having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 81)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_with_Invalid_paymentChequeDate_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto with invalid Payment Cheque Date field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("Jan-30-2024");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getPaymentChequeDate_errorMsg();
        String expected_errorMsg = "Invalid Date Format";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent with invalid Payment Cheque Date field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent with invalid Payment Cheque Date field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent with invalid Payment Cheque Date field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent with invalid Payment Cheque Date field. having complaint no - " +complaintNo);
        }
    }
    @Test(priority = 82)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_without_InsurerResolutionLetterDate_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto without Insurer’s resolution letter date field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getInsurersResolutionLetterDate_errorMsg();
        String expected_errorMsg = "Please Enter Insurer’s resolution letter date";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent without Insurer’s resolution letter date field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent without Insurer’s resolution letter date field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent without Insurer’s resolution letter date field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent without Insurer’s resolution letter date field. having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 83)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_with_Invalid_InsurersResolutionLetterDate_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto with invalid  Insurer’s resolution letter date field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("");
        vcd.enterInsurersResolutionLetterDate("Jan-30-2024");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getInsurersResolutionLetterDate_errorMsg();
        String expected_errorMsg = "Invalid Date Format";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent with invalid Insurer’s resolution letter date field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent with invalid Insurer’s resolution letter date field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent with invalid Insurer’s resolution letter date field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent with invalid Insurer’s resolution letter date field. having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 84)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_without_DateOfHonoringTheService_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto without Date Of Honoring The Service field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("");
        vcd.enterInsurersResolutionLetterDate("");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getDateOfHonoringTheService_errorMsg();
        String expected_errorMsg = "Please Enter Date of Honoring the service";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent without Date Of Honoring  The Service field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent without Date Of Honoring  The Service field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent without Date Of Honoring  The Service field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent without Date Of Honoring  The Service field. having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 85)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_with_Invalid_DateOfHonoringTheService_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Closure Request To IRDAI from Reopen to Attendedto with invalid  Date Of Honoring  The Service field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("");
        vcd.enterInsurersResolutionLetterDate("");
        vcd.enterDateOfHonoringTheService("Jan-30-2024");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getDateOfHonoringTheService_errorMsg();
        String expected_errorMsg = "Invalid Date Format";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Closure Request to IRDAI can not sent with invalid Date Of Honoring  The Service field. having complaint no - "+complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI can not sent with invalid Date Of Honoring  The Service field. having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI sent with invalid Date Of Honoring  The Service field. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI sent with invalid Date Of Honoring  The Service field. having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 86)
    public void Verify_Closure_RequestToIRDAI_ReopenToAttendedToStatus_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify Closure Request To IRDAI.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("");
        vcd.enterInsurersResolutionLetterDate("");
        vcd.enterDateOfHonoringTheService("");
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Closure Request to IRDAI sent Successfully. having complaint no" + complaintNo);
            logger.info("Test Pass:Closure Request to IRDAI sent Successfully. having complaint no"+complaintNo);
        } else {
            test.log(Status.FAIL, "Closure Request to IRDAI is not sent Successfully. having complaint no -"+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Closure Request to IRDAI is not sent Successfully. having complaint no"+complaintNo);
        }
    }
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
