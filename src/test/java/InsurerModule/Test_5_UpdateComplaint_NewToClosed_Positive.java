package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.*;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test_5_UpdateComplaint_NewToClosed_Positive extends MainClass {
    Properties properties;
    List<String> lis;
    @Test(priority = 15)
    public void Verify_UpdateComplaint_NewToAcknowledge_Positive() throws IOException, InterruptedException {
        test = extent.createTest("Positive: Verify Update Complaint NewToAcknowledge Positive");
        lis = register();
        // launch admin login page
        loadURL("LoginURL");
        //read cred from prop file and login with valid credentials
        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("Hdfc_Ergo_User_Id"), property.getProperty("Hdfc_Ergo_Password"));

        //Menu-->Complaints-->View/Edit complaints
        SidebarHeader sbr = new SidebarHeader(driver);
        Thread.sleep(1000);
        sbr.clickMenu();
        Thread.sleep(1000);
        sbr.clickcomplaintSidebarTab();
        Thread.sleep(1000);
        sbr.clickviewEditcomplaintSidebarTab();

        Thread.sleep(1000);
        //Menu-->Complaints-->View/Edit complaints-->Advance Search
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        //Complaint Details Page
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        Thread.sleep(1000);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enter_Value_Branch_Code_field(2);
        vcd.enter_Value_State_Name_Field(1);
        vcd.clkDistric();
        Thread.sleep(1000);
        vcd.downArrow();
        Thread.sleep(1000);
        vcd.enterKeyBoard();
        vcd.click_Complaint_Status_Dropdown("Acknowledged");
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint Updated from New To Acknowledge status successfully. having complaint No "+complaintNo);
            logger.info("Test Pass:Complaint Updated from New To Acknowledge status successfully. having complaint No "+complaintNo);
        } else {
            test.log(Status.FAIL, "Complaint is not Updated from New To Acknowledge status. having complaint No "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint is not Updated from New To Acknowledge status. having complaint No "+complaintNo);

        }

    }
     @Test(priority = 16)
    public void Verify_ComplaintUpdateAckToPending_NoBtnConfirmPopup_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Functionality of No button on popup of Update Complaint from Acknowledge To Pending.");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Pending");
        vcd.click_Update_Button();
        if(vcd.click_Update_Record_Cancel()){
            test.log(Status.PASS, "No button of confirm popup is working properly when Complaint Updated from Acknowledge To Pending status. having complaint No "+lis.get(10));
            logger.info("Test Pass:No button of confirm popup is working properly when Complaint Updated from Acknowledge To Pending status. having complaint No "+lis.get(10));
        } else {
            test.log(Status.FAIL, "No button of confirm popup is not working properly when Complaint Updated from Acknowledge To Pending status. having complaint No "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: No button of confirm popup is not working properly when Complaint Updated from Acknowledge To Pending status.  having complaint No "+lis.get(10));

        }
    }
    @Test(priority = 17)
    public void Verify_ComplaintUpdateAckToPending_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify Update Complaint Acknowledge To Pending.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint Updated from Acknowledge To Pending status successfully. having complaint No "+lis.get(10));
            logger.info("Test Pass:Complaint Updated from Acknowledge To Pending status successfully. having complaint No "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is not Updated from Acknowledge To Pending status.  having complaint No "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint is not Updated from Acknowledge To Pending status.  having complaint No "+lis.get(10));

        }
    }
   @Test(priority = 18)
    public void Verify_ComplaintUpdatePendingToAttendedTo_WithRemarkRegEntity_HavingSpecialChar_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Update Complaint Pending To Attended_to With Remarks br Regulated Entity having Special Char.");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Attended to");
        vcd.enter_Value_Remark_by_Regulated_Entity_Field("Test@%$$entity");
        vcd.click_Type_of_Disposal_Infavour_Button();
        String actual_errorMsg = vcd.getRemarkbyRegulatedEntityError();
        String expected_errorMsg = "Please Enter Valid Remarks by Regulated Entity";

        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Validation of error message of special char at Remarks be Reg Entity field from complaint update from pending to attended_to is successful. having complaint No "+lis.get(10));
            logger.info("Test Pass:Validation of error message of special char at Remarks be Reg Entity field from complaint update from pending to attended_to is successful. having complaint No "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Validation of error message of special char at Remarks be Reg Entity field from complaint update from pending to attended_to is not successful. having complaint No "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Validation of error message of special char at Remarks be Reg Entity field from complaint update from pending to attended_to is not successful. having complaint No "+lis.get(10));

        }
    }
    @Test(priority = 19)
    public void Verify_ComplaintUpdatePendingToAttendedTo_maxLength_exceeding_RemarkRegEntity_field(){
        test = extent.createTest("Negative: Verify Update Complaint from PendingToAttendedTo with maxLength exceeding RemarkRegEntity field");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_Remark_by_Regulated_Entity_Field(generateRandomString(4005));
        String expected_errorMsg = "You have reached your maximum limit of characters allowed";
        String actual_errorMsg ="";
        //Logic to handle multiple alerts
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); // wait for up to 10 seconds
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
        if(expected_errorMsg.equals(actual_errorMsg)){
            test.log(Status.PASS, "Validation of error message of exceeding maxLength  at Remarks be Reg Entity field from complaint update from pending to attended_to is successful. having complaint No "+lis.get(10));
            logger.info("Test Pass:Validation of error message of exceeding maxLength  at Remarks be Reg Entity field from complaint update from pending to attended_to is successful. having complaint No "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Validation of error message of exceeding maxLength  at Remarks be Reg Entity field from complaint update from pending to attended_to is not successful. having complaint No "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: alidation of error message of exceeding maxLength  at Remarks be Reg Entity field from complaint update from pending to attended_to is not successful. having complaint No "+lis.get(10));

        }
    }
    @Test(priority = 20)
    public void Verify_ComplaintUpdatePendingToAttendedTo_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify Update Complaint Pending To Attended_to.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.enter_Value_Remark_by_Regulated_Entity_Field("Remark for Regulated entity");
        vcd.click_Type_of_Disposal_Infavour_Button();
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint Updated from Pending To Attended_to status successfully. having complaint No "+lis.get(10));
            logger.info("Test Pass:Complaint Updated from Pending To Attended_to status successfully. having complaint No "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is not Updated from Pending To Attended_to status. having complaint No "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint is not Updated from Pending To Attended_to status. having complaint No "+lis.get(10));
        }
    }
    @Test(priority = 21)
    public void Verify_ComplaintUpdateAttendedToClosed_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify Update Complaint Attended_to To Closed.");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Closed");
        String expectedMsg = "Complaint cannot be closed as it has not completed 60 days of time duration.";
        String actualMsg = driver.switchTo().alert().getText();
        if(expectedMsg.equals(actualMsg)){
            test.log(Status.PASS, "Complaint Updated from Attended_to To Closed status cannot be closed, as it has not completed 60 days of time duration. having complaint No "+lis.get(10));
            logger.info("Test Pass:Complaint Updated from Attended_to To Closed status cannot be closed, as it has not completed 60 days of time duration. having complaint No "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is not Updated from Attended_to To Closed status.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint is not Updated from Attended_to To Closed status.");
        }
    }
    public List<String> register() throws IOException, InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegTestData");
        List<String> ls = new ArrayList<>();
        int columnCount = sheet.getRow(0).getLastCellNum();
        XSSFRow row = sheet.getRow(1);
        String mobile_no = generateRandomMobileNumber();
        for (int c = 0; c < columnCount; c++) {
            XSSFCell cell = row.getCell(c);
            if (cell != null) {
                cell.setCellType(CellType.STRING); // Ensure cell type is String
                ls.add(cell.toString());
            }
        }
        loadURL("RegPageUrl");

        ComplaintRegPage reg = new ComplaintRegPage(driver);
        reg.enterName(ls.get(0));
        reg.enterMobileNumber(mobile_no);
        reg.emailText();

        //Adding wait of 3 seconds to available OTP window
        Thread.sleep(3000);

        HomePage h = new HomePage(driver);
        h.enterOTP(mobile_no);
        reg.enterPinCode(ls.get(2));
        reg.scrollToComplaint();
        reg.complaint_Against(ls.get(3));
        reg.insCompanyName(ls.get(4));
        reg.policyType(ls.get(5));
        reg.complaintType(ls.get(6));
        reg.complaintDescType(ls.get(7));
        reg.policyNumberClick();
        reg.enterPolicyNumber(ls.get(8));
        reg.enterCompDesc(ls.get(9));
        reg.registerClick();
        reg.acceptAlertWindow();
        Thread.sleep(3000);
        ls.add(reg.getComplaintNumber());

        return ls;
    }
}
