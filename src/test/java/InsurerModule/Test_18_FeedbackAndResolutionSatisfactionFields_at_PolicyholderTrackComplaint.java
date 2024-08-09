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

public class Test_18_FeedbackAndResolutionSatisfactionFields_at_PolicyholderTrackComplaint extends MainClass {
    List<String> lis;
    String mobile_no;
    @Test(priority = 76)
    public void Verify_Functionality_FeedbackAndResolutionSatisfactionFields_at_PolicyholderTrackComplaint_Positive() throws IOException, InterruptedException {
        test = extent.createTest("Positive: Verify Functionality Feedback and Resolution Satisfaction Fields at Policyholder TrackComplaint.");
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
        vcd.clickToOkButtonConfirmUpdate();

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
        if(vcdAtAttendedTo.clickToOkButtonConfirmUpdate()) {
            CallcenterRegPage cr = new CallcenterRegPage(driver);
            cr.logOutSuccess();
            Thread.sleep(2000);
            //Track Policy
            loadURL("Url");
            HomePage h = new HomePage(driver);
            h.OkPopup();
            Thread.sleep(3000);

            h.enterMobileNumber(mobile_no);
            h.trackComplaint();
            h.OkPopup();
            h.enterOTP(mobile_no);

            TrackComplaint_Page t = new TrackComplaint_Page(driver);
            t.view_edit_button_click();
            scrollToComplaint();
            t.selectFeedbackForIGMSPortalEaseOfUse();
            Thread.sleep(1000);
            t.downArrow();
            Thread.sleep(1000);
            t.enterKeyBoard();
            t.selectResolutionSatisfactionLevel();
            Thread.sleep(1000);
            t.downArrow();
            Thread.sleep(1000);
            t.enterKeyBoard();
            t.update_complaint_desc();
            t.SelectYesBtn_click();
            if (t.click_ok_with_return_value()) {
                t.clickLogout();
                t.click_Yes_Confirm_popup();
                test.log(Status.PASS, "Feedback and Resolution Satisfaction fields added Successfully at PolicyholderTrackComplaint.");
                logger.info("Test Pass:Feedback and Resolution Satisfaction fields added Successfully at PolicyholderTrackComplaint.");
            } else {
                test.log(Status.FAIL, "Feedback and Resolution Satisfaction fields is not added Successfully at PolicyholderTrackComplaint.");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Fail:Feedback and Resolution Satisfaction fields is not added Successfully at PolicyholderTrackComplaint.");
            }
        }

    }
    @Test(priority = 77)
    public void Validate_FeedbackAndResolutionSatisfactionFields_at_ComplaintDetailPage() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Validate Feedback and Resolution Satisfaction Fields at ComplaintDetailPage.");

        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("Hdfc_Ergo_User_Id"), property.getProperty("Hdfc_Ergo_Password"));

        //Menu-->Complaints-->View/Edit complaints
        SidebarHeader sbr = new SidebarHeader(driver);
        sbr.clickMenu();
        Thread.sleep(1000);
        sbr.clickcomplaintSidebarTab();
        Thread.sleep(1000);
        sbr.clickviewEditcomplaintSidebarTab();
        //Navigate to Adv Search page
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        //Navigate to Complaint Details page
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        if(vcd.visibility_FeedbackAndResolutionSatisfactionFields_CompDetailsPage()){
            test.log(Status.PASS, "Feedback and Resolution Satisfaction fields is visible at Complaint Details Page. with complaint no " +lis.get(10));
            logger.info("Test Pass:Feedback and Resolution Satisfaction fields is visible at Complaint Details Page. with complaint no" +lis.get(10));
        } else {
            test.log(Status.FAIL, "Feedback and Resolution Satisfaction fields is not visible at Complaint Details Page. with complaint no " +lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Feedback and Resolution Satisfaction fields is not visible at Complaint Details Page. with complaint no " +lis.get(10));
        }
    }
    public List<String> register() throws IOException, InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegTestData");
        mobile_no = generateRandomMobileNumber();
        System.out.println(mobile_no);
        List<String> ls = new ArrayList<>();

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
