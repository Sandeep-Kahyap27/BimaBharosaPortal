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
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test_15_TypeOfDisposal_ComplaintUpdatePendingToAttendedTo_Positive extends MainClass {
    List<String> lis;
    @Test(priority =56)
    public void Verify_complaintUpdate_PendingToAttendedTo_without_TypeOfDisposal_Negative() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify Complaint Update from Pending To AttendedTo Without Type of Disposal.");
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

        //Update complaint New to Acknowledge
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
        vcd.click_Complaint_Updated_Successfully_Button();

        //Update complaint Acknowledge to Pending
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Update complaint Pending to attended_to
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Update_Button();
        if(vcdAtAttendedTo.clickToTypeOfDisposalMandatory_errorMsg()){
            test.log(Status.PASS, "Complaint is not Updated from Pending To Attended_to status without typeOfDisposal. having complaint no - "+lis.get(10));
            logger.info("Test Pass:Complaint is not Updated from Pending To Attended_to status without typeOfDisposal. having complaint no - "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is Updated from Pending To Attended_to status without typeOfDisposal. having complaint no - "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is Updated from Pending To Attended_to status without typeOfDisposal. having complaint no - "+lis.get(10));

        }
    }
    @Test(priority = 57)
    public void Verify_Infavour_TypeOfDisposal_atComplaintUpdate_PendingToAttendedTo_Positive() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Infavour TypeOfDisposal in Complaint Update from Pending To AttendedTo.");
        lis = register();

        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();

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

        //Update complaint New to Acknowledge
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
        vcd.click_Complaint_Updated_Successfully_Button();

        //Update complaint Acknowledge to Pending
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Update complaint Pending to Attended_to
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Type_of_Disposal_Infavour_Button();
        vcdAtAttendedTo.click_Update_Button();
        vcdAtAttendedTo.clickToYesButtonToConfirmRecordUpdate();
        if(vcdAtAttendedTo.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint Updated from Pending To Attended_to status with Infavour typeOfDisposal Successfuly. having complaint no - "+lis.get(10));
            logger.info("Test Pass:Complaint Updated from Pending To Attended_to status with Infavour typeOfDisposal Successfuly. having complaint no - "+lis.get(10));
        } else {
            test.log(Status.PASS, "Complaint is not Updated from Pending To Attended_to status with Infavour typeOfDisposal. having complaint no - "+lis.get(10));
            logger.error("Test Fail:Complaint is not Updated from Pending To Attended_to status with Infavour typeOfDisposal. having complaint no - "+lis.get(10));
        }
    }

   // @Test(priority = 58)
    public void Verify_PartiallyInfavour_TypeOfDisposal_atComplaintUpdate_PendingToAttendedTo_Positive() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Partially Infavour TypeOfDisposal in Complaint Update from Pending To AttendedTo.");
        lis = register();

        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();

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

        //Update complaint New to Acknowledge
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
        vcd.click_Complaint_Updated_Successfully_Button();

        //Update complaint Acknowledge to Pending
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Update complaint Pending to Attended_to
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Type_of_Disposal_PartialInfavour_Button();
        vcdAtAttendedTo.click_Update_Button();
        vcdAtAttendedTo.clickToYesButtonToConfirmRecordUpdate();
        if(vcdAtAttendedTo.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint Updated from Pending To Attended_to status with Partially Infavour typeOfDisposal Successfuly.having complaint no - "+lis.get(10));
            logger.info("Test Pass:Complaint Updated from Pending To Attended_to status with Partially Infavour typeOfDisposal Successfuly.having complaint no - "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is not Updated from Pending To Attended_to status with Partially Infavour typeOfDisposal.having complaint no - "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is not Updated from Pending To Attended_to status with Partially Infavour typeOfDisposal.having complaint no - "+lis.get(10));
        }
    }

  //  @Test(priority = 59)
    public void Verify_NotInfavour_TypeOfDisposal_atComplaintUpdate_PendingToAttendedTo_Positive() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Not In Favour TypeOfDisposal in Complaint Update from Pending To AttendedTo.");
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

        //Update complaint New to Acknowledge
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
        vcd.click_Complaint_Updated_Successfully_Button();

        //Update complaint Acknowledge to Pending
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Update complaint Pending to Attended_to
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Type_of_Disposal_NotInfavour_Button();
        vcdAtAttendedTo.click_Update_Button();
        vcdAtAttendedTo.clickToYesButtonToConfirmRecordUpdate();
        if(vcdAtAttendedTo.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint Updated from Pending To Attended_to status with Not In favour typeOfDisposal Successfuly.having complaint no - "+lis.get(10));
            logger.info("Test Pass:Complaint Updated from Pending To Attended_to status with Not In favour typeOfDisposal Successfuly.having complaint no - "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is not Updated from Pending To Attended_to status with Not In favour typeOfDisposal. having complaint no - "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is not Updated from Pending To Attended_to status with Not In favour typeOfDisposal.having complaint no - "+lis.get(10));
        }
    }

    @Test(priority = 60)
    public void Verify_ComplaintUpdate_PendingToAttended_without_OriginalIRDAITokenNo_Negative() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify Complaint Update from Pending To AttendedTo Without OriginalIRDAITokenNo.");
        lis = register();

        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();

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

        //Update complaint New to Acknowledge
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
        vcd.click_Complaint_Updated_Successfully_Button();

        //Update complaint Acknowledge to Pending
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Update complaint Pending to Attended_to
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Type_of_Disposal_Duplicate_Button();
        vcdAtAttendedTo.click_Update_Button();
        String expected_errorMsg = "Please Enter Original IRDA Token Number";
        String actual_errorMsg = vcdAtAttendedTo.getOriginalIRDAITokenNo_ErrorMsg();
        if(expected_errorMsg.equals(actual_errorMsg)){
            test.log(Status.PASS, "Complaint is not Updated from Pending To Attended_to status without Original IRDAI Token No.having complaint no - "+lis.get(10));
            logger.info("Test Pass:Complaint is not Updated from Pending To Attended_to status without Original IRDAI Token No.having complaint no - "+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is Updated from Pending To Attended_to status without Original IRDAI Token No.having complaint no - "+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is Updated from Pending To Attended_to status without Original IRDAI Token No.having complaint no - "+lis.get(10));
        }
    }
      @Test(priority = 61)
    public void Verify_Duplicate_TypeOfDisposal_atComplaintUpdate_PendingToAttendedTo_Positive() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Complaint Update from Pending To AttendedTo With Duplicate Type of Disposal.");
        lis = register();

        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();

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
        String original_irdai_token_number = vcd.addOneToLastDigitOfComplaintNo(lis.get(10));

        //Update complaint New to Acknowledge
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
        vcd.click_Complaint_Updated_Successfully_Button();

        //Update complaint Acknowledge to Pending
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Update complaint Pending to Attended_to
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Type_of_Disposal_Duplicate_Button();
        vcdAtAttendedTo.enterOriginalIRDAITokenNo(original_irdai_token_number);
        vcdAtAttendedTo.click_Update_Button();
        vcdAtAttendedTo.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
          test.log(Status.PASS, "Complaint Update from Pending To AttendedTo With Duplicate Type of Disposal. having complaint no" + lis.get(10));
          logger.info("Test Pass:Complaint Update from Pending To AttendedTo With Duplicate Type of Disposal. having complaint no"+lis.get(10));
        } else {
          test.log(Status.FAIL, "Complaint is not Updated from Pending To AttendedTo With Duplicate Type of Disposal. having complaint no -"+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Complaint is not Updated from Pending To AttendedTo With Duplicate Type of Disposal. having complaint no"+lis.get(10));
        }
    }

    public List<String> register() throws IOException, InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegTestData");

        List<String> ls = new ArrayList<>();
        String mobile_no = generateRandomMobileNumber();
        int columnCount = sheet.getRow(0).getLastCellNum();
        XSSFRow row = sheet.getRow(1);
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
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
