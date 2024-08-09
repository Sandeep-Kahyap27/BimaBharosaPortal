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

public class Test_13_Pen_Status_PolicyDetailsbyInsurerPage extends MainClass {
    List<String> ls;

    @Test(priority = 50)
    public void Policy_no_field_special_characters_functionality_Negative() throws IOException, InterruptedException {
        ls = register();
        test = extent.createTest("Negative: Verify Policy no field special characters functionality");
        loadURL("LoginURL");
        //Login with Credentials
        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("Hdfc_Ergo_User_Id"), property.getProperty("Hdfc_Ergo_Password"));
        SidebarHeader sbr = new SidebarHeader(driver);
        sbr.clickMenu();
        Thread.sleep(1000);
        sbr.clickcomplaintSidebarTab();
        Thread.sleep(1000);
        sbr.clickviewEditcomplaintSidebarTab();
        Thread.sleep(1000);

        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.clickPendingStatus();
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        scrollToComplaint();
        vcd.clkComplaintcheckbox();
        scrollToComplaint();

        vcd.enter_policy_number("@#%#123");
        vcd.click_policy_type_field();
        String errormsg = "Please Enter Valid Policy Number";
        if ((vcd.enter_special_character_policy_no_field()).equals(errormsg)) {
            test.log(Status.PASS, "Special characters enters in policy number field");
            logger.info("Test Pass: Special characters enters in policy number field");
        } else {
            test.log(Status.FAIL, "Special characters is not enters in policy number field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Special characters is not enters in policy number field");
        }
    }

    @Test(priority = 51)
    public void Verify_Policy_no_field_is_blank_functionality_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify Policy no field is blank functionality.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_policy_number("");

        if (vcd.verify_policy_no_blank_field_error_message()) {
            test.log(Status.PASS, "Error message displayed for blank policy number field");
            logger.info("Test Pass: Error message displayed for blank policy number field");
        } else {
            test.log(Status.FAIL, "Error message is not displayed for blank policy number field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Error message is not displayed for blank policy number field");
        }

    }

    @Test(priority = 52)
    public void Verify_complaint_type_field_unselected_functionality_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify complaint type field unselected functionality");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_policy_number("1234");
        vcd.enter_value_policy_type_field("Health Insurance");
        scrollToComplaint();
        vcd.click_Update_Button();
        String expectedErrMsg = "Please Select Commplaint Type";
        if (vcd.complaint_type_error_msg().equals(expectedErrMsg)) {
            test.log(Status.PASS, "Complaint type field is unselected");
            logger.info(" Test Pass: Complaint type field is unselected");
        } else {
            test.log(Status.FAIL, "Complaint type field is selected");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint type field is selected");
        }
    }

    @Test(priority = 53)
    public void Verify_complaint_Desc_type_field_unselected_functionality_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify complaint Description type field unselected");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_policy_number("1234");
        vcd.enter_value_policy_type_field("Health Insurance");
        vcd.enter_value_complaint_type_field("Proposal Related");
        scrollToComplaint();
        vcd.click_Update_Button();
        String expectedrrmsg = "This field is required.";
        if (vcd.complaint_desc_error_msg().equals(expectedrrmsg)) {
            test.log(Status.PASS, "Complaint description type field is unselected");
            logger.info("Test pass :Complaint description type field is unselected");
        } else {
            test.log(Status.FAIL, "Complaint description type field is selected");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint description type field is selected");
        }
    }

    @Test(priority = 54)
    public void Verify_Policy_details_insurer_field_functionality_Positive() throws IOException, InterruptedException {
        test = extent.createTest("Positive: Verify clear button functionality");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_policy_number("24567");
        vcd.enter_value_policy_type_field("Health Insurance");
        vcd.enter_value_complaint_type_field("Proposal Related");
        vcd.enter_value_complaint_description_type("Insurer accepted premium and then rejected the proposal");
        scrollToComplaint();
        vcd.click_Update_Button();
        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(3000);
        String expectedmsg = "Complaint Updated Successfully";
        if (vcd.get_success_message().equals(expectedmsg)) {
            test.log(Status.PASS, "complaint updated successfully for policy details by insurer field");
            logger.info("Test Pass :complaint updated successfully for policy details by insurer field");
        } else {
            test.log(Status.FAIL, "complaint is not updated successfully for policy details by insurer field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: complaint is not updated successfully for policy details by insurer field");
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



