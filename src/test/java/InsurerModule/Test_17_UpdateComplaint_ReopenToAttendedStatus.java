package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.*;
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

public class Test_17_UpdateComplaint_ReopenToAttendedStatus extends MainClass {
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
    @Test(priority = 63)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_withSpecialCharater_RemarksByRegulatedEntity_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto with Special Character in Final Resolution Remarks.");
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
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Attended to");
        vcd.enter_Value_Remark_by_Regulated_Entity_Field("Test@Remarks");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getRemarkbyRegulatedEntityError();
        String expected_errorMsg = "Please Enter Valid Final Resolution Remarks";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status with special Character in  Final Resolution Remarks field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status with with special Character in Final Resolution Remarks field having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint updated from Reopen to AttendeTo status with with special Character in  Final Resolution Remarks field having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status with with special Character in  Final Resolution Remarks field having complaint no - " +complaintNo);
        }
    }
    @Test(priority = 64)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_without_RemarksByRegulatedEntity_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto without Remarks By Regulated Entity.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getRemarkbyRegulatedEntityError();
        String expected_errorMsg = "Please Enter Valid Remarks by Regulated Entity";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status without Remarks by Regulated Entity field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status without Remarks by Regulated Entity field having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint updated from Reopen to AttendeTo status without Remarks by Regulated Entity field having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status without Remarks by Regulated Entity field having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 65)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_withExceedingLimit_RemarksByRegulatedEntity_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto with exceeding limit in Remarks by Regulated Entity field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
//        vcd.click_View_Edit_Complaints_Checkbox();
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.enter_Value_Remark_by_Regulated_Entity_Field(generateRandomString(4005));
        String actual_errorMsg ="";
        //Logic to handle multiple alerts
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); // wait for up to 1 seconds
        for (int i = 0; i < 10; i++) { // Arbitrary upper limit to prevent infinite loop
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                actual_errorMsg = alert.getText();
                alert.accept(); // Accept the alert
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No more alerts present");
                break;
            }
        }
        String expected_errorMsg = "You have reached your maximum limit of characters allowed";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status with exceeding limit in Remarks by Regulated Entity field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status with with exceeding limit in Remarks by Regulated Entity field having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint updated from Reopen to AttendeTo status with exceeding limit in  Remarks by Regulated Entity field having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status with exceeding limit in  Remarks by Regulated Entity field having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 66)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_without_FinalResolutionRemark_Negative() {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto without Final Resolution Remarks.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_Remark_by_Regulated_Entity_Field("Remarks by regulated entity is added");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getFinalResolutionRemarksErrorMsg();
        String expected_errorMsg = "Please Enter Final Resolution Remarks";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status without Final Resolution Remarks field ");
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status without Final Resolution Remarks field ");
        } else {
            test.log(Status.PASS, "Complaint updated from Reopen to AttendeTo status without Final Resolution Remarks field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status without Final Resolution Remarks field.");
        }
    }
    @Test(priority = 67)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_withSpecialCharater_FinalResolutionRemark_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto with Special Character in Final Resolution Remarks.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterFinalResolutionRemarks("Test@Remarks");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getFinalResolutionRemarksErrorMsg();
        String expected_errorMsg = "Please Enter Valid Final Resolution Remarks";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status with special Character in  Final Resolution Remarks field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status with with special Character in Final Resolution Remarks field having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint updated from Reopen to AttendeTo status with with special Character in  Final Resolution Remarks field having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status with with special Character in  Final Resolution Remarks field having complaint no - " +complaintNo);
        }
    }

    @Test(priority = 68)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_withExceedingLimit_FinalResolutionRemark_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto with exceeding limit in Final Resolution Remarks.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.enterFinalResolutionRemarks(generateRandomString(4005));
        String actual_errorMsg ="";
        //Logic to handle multiple alerts
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); // wait for up to 1 seconds
        for (int i = 0; i < 10; i++) { // Arbitrary upper limit to prevent infinite loop
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                actual_errorMsg = alert.getText();
                alert.accept(); // Accept the alert
            } catch (Exception e) {
                break;
            }
        }
        String expected_errorMsg = "You have reached your maximum limit of characters allowed";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status with exceeding limit in  Final Resolution Remarks field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status with with exceeding limit in Final Resolution Remarks field having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint updated from Reopen to AttendeTo status with exceeding limit in  Final Resolution Remarks field having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status with exceeding limit in  Final Resolution Remarks field having complaint no - " +complaintNo);
        }

    }

    @Test(priority = 69)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_without_FinalResolutionRemarkDate_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto with Special Character in Final Resolution Remarks.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterFinalResolutionRemarks("Test Remarks");
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getFinalResolutionRemarksDate_errorMsg();
        String expected_errorMsg = "Please Enter Final Resolution Remark Date";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status without  Final Resolution Remarks date field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status without Final Resolution date Remarks field having complaint no - " +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint updated from Reopen to AttendeTo status without Final Resolution Remarks date field having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint updated from Reopen to AttendeTo status without  Final Resolution Remarks date field having complaint no - " +complaintNo);
        }
    }

   @Test(priority = 70)
    public void Verify_UpdateComplaint_ReopenToAttendedToStatus_with_invalidDate_FinalResolutionRemarkDate_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify update complaint from Reopen to Attendedto with InvalidDate in Final Resolution Remarks Date field.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enterFinalResolutionRemarkDateField("Jan-30-2024");
        vcd.enterFinalResolutionRemarks("Test Remarks");
        vcd.click_Type_of_Disposal_Duplicate_Button();
        Thread.sleep(3000);
        String actual_errorMsg = vcd.getFinalResolutionRemarksDate_errorMsg();
        String expected_errorMsg = "Invalid Date Format";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint is not updated from Reopen to AttendeTo status with Invalid Date in Final Resolution Remarks date field having complaint no - "+complaintNo);
            logger.info("Test Pass:Complaint is not updated from Reopen to AttendeTo status with Invalid Date in Final Resolution Remarks date field having complaint no -" +complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint is updated from Reopen to AttendeTo status with Invalid Date in Final Resolution Remarks date field having complaint no "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is  updated from Reopen to AttendeTo status with Invalid Date in Final Resolution Remarks date field having complaint no - " +complaintNo);
        }
    }

   @Test(priority = 71)
    public void Validate_functionality_uploadAttachment_at_ReopenToAttendedToStatus_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Validate Upload Attachment at Reopen to AttendedTo status.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.click_upload_attachment_field(System.getProperty("user.dir")+"/src/main/resources/TestData/dummy-pdf_2.pdf");
        vcd.click_Upload_Button();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Doccument/Attachment is upload Successfully at ReopenToAttended_to complaint Status.having complaint no - "+complaintNo);
            logger.info("Test Pass:Doccument/Attachment is upload Successfully at ReopenToAttended_to complaint Status.having complaint no - "+complaintNo);
        } else {
            test.log(Status.FAIL, "Doccument/Attachment is upload Successfully at ReopenToAttended_to complaint Status.having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Doccument/Attachment is upload Successfully at ReopenToAttended_to complaint Status.having complaint no - "+complaintNo);
        }
    }

   @Test(priority = 72)
    public void Validate_functionality_uploadAttachment_withInvalidateFormat_ReopenToAttendedToStatus_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Validate Upload Attachment at Reopen to AttendedTo status.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.click_upload_attachment_field(System.getProperty("user.dir")+"/src/main/resources/TestData/ComplaintRegistration.xlsx");
        vcd.click_Upload_Button();
        if(vcd.clickToTypeOfDisposalMandatory_errorMsg()){
            test.log(Status.PASS, "Doccument/Attachment is not uploaded as it is not in jpeg, pdf, jpg, png format "+complaintNo);
            logger.info("Test Pass:Doccument/Attachment is not uploaded as it is not in jpeg, pdf, jpg, png format "+complaintNo);
        } else {
            test.log(Status.FAIL, "Doccument/Attachment is upload Successfully at ReopenToAttended_to complaint Status.having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Doccument/Attachment is upload Successfully at ReopenToAttended_to complaint Status.having complaint no - "+complaintNo);
        }
    }

  @Test(priority = 73)
    public void Validate_updateComplaint_ReopenToAttendedToStatus_without_FinalResolutionCheckbox_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Validate Upload Attachment at Reopen to AttendedTo status.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        vcd.enterFinalResolutionRemarks("Test Data");
        vcd.enterFinalResolutionRemarkDateField("14-Jun-2024");
        crg.scrollToComplaint();
        vcd.click_upload_attachment_field(System.getProperty("user.dir")+"/src/main/resources/TestData/dummy-pdf_2.pdf");
        vcd.click_Upload_Button();
        vcd.clickToOkButtonConfirmUpdate();
        crg.scrollToComplaint();
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getFinalResolutionDoccument_errorMsg();
        String expected_errorMsg = "Please Upload Final Resolution Document";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Complaint can not update from Reopen to Attended_to without checking Final Resolution Doccument Checkbox. having complaint no"+complaintNo);
            logger.info("Test Pass:Complaint can not update from Reopen to Attended_to without checking Final Resolution Doccument Checkbox. having complaint no"+complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint is updated from Reopen to Attended_to without checking Final Resolution Doccument Checkbox. having complaint no"+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is updated from Reopen to Attended_to without checking Final Resolution Doccument Checkbox. having complaint no"+complaintNo);
        }
    }

  @Test(priority = 74)
    public void Validate_Visibility_IFFinalResolutionLetterCheckbox_ReopenToAttendedToStatus_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Validate Visibility of IF Final Resolution Letter Checkbox ReopenToAttendedToStatus.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.enterFinalResolutionRemarks("Test Data");
        vcd.enterFinalResolutionRemarkDateField();
        crg.scrollToComplaint();
        vcd.click_iF_final_resolution_letter_button();
        if(vcd.Visibility_iF_final_resolution_letter_button()){
            test.log(Status.PASS, "IF Final Resolution Letter Checkbox is visible Succeefully at ReopenToAttendedTo Status. having complaint no" + complaintNo);
            logger.info("Test Pass:IF Final Resolution Letter Checkbox is visible Succeefully at ReopenToAttendedTo Status. having complaint no"+complaintNo);
        } else {
            test.log(Status.FAIL, "IF Final Resolution Letter Checkbox is not visible Succeefully at ReopenToAttendedTo Status. having complaint no -"+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:IF Final Resolution Letter Checkbox is not visible Succeefully at ReopenToAttendedTo Status. having complaint no"+complaintNo);
        }
    }
    @Test(priority = 75)
    public void Validate_updateComplaint_ReopenToAttendedToStatus_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Validate Upload Attachment at Reopen to AttendedTo status.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.enterFinalResolutionRemarks("Test Data");
        vcd.enterFinalResolutionRemarkDateField();
        crg.scrollToComplaint();
        vcd.click_iF_final_resolution_letter_button();
        vcd.click_upload_attachment_field(System.getProperty("user.dir")+"/src/main/resources/TestData/dummy-pdf_2.pdf");
        vcd.click_Upload_Button();
        vcd.clickToOkButtonConfirmUpdate();
        crg.scrollToComplaint();
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint  updated Successfully  from Reopen to Attended_to. having complaint no" + complaintNo);
            logger.info("Test Pass:Complaint  updated Successfully  from Reopen to Attended_to. having complaint no"+complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint  is not updated Successfully  from Reopen to Attended_to. having complaint no -"+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint is not updated Successfully  from Reopen to Attended_to. having complaint no"+complaintNo);
        }
    }
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}

