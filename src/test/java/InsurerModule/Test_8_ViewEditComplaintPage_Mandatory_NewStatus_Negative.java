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


public class Test_8_ViewEditComplaintPage_Mandatory_NewStatus_Negative extends MainClass {
    List<String> ls;
    @Test(priority = 26)
    public void test_Branch_Code_Negative() throws IOException, InterruptedException {
        test = extent.createTest("Negative: Verify Branch code field without selection");
        //New complaint registration
        ls =register();

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
        Thread.sleep(1000);

        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(ls.get(10));
        asp.clickSearchBtn();

        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Acknowledged");
        scrollToComplaint();
        vcd.click_Update_Button();
        String errormsg = "Please Select Branch";
        if ((vcd.Get_branch_code_Error_Message()).equals(errormsg)) {
            test.log(Status.PASS, "branch code is not selected in complaint details field");
            logger.info("Test Pass: branch code is not selected in complaint details field");
        } else {
            test.log(Status.FAIL, "branch code is selected in complaint details field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: branch code is selected in complaint details field");
        }
    }

    @Test(priority = 27, dependsOnMethods = "test_Branch_Code_Negative")
    public void Verify_state_functionaliy_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify State code field without selection.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_Branch_Code_field(2);
        vcd.click_Update_Button();
        Thread.sleep(3000);
        String errormsg = "Please Select State";
        if ((vcd.state_Name_Error_Message()).equals(errormsg)) {
            test.log(Status.PASS, "state code is not selected in complaint details field");
            logger.info("Test Pass: state code is not selected in complaint details field");
        } else {
            test.log(Status.FAIL, "state code is selected in complaint details field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: state code is selected in complaint details field");
        }
    }

    @Test(priority = 28, dependsOnMethods = "Verify_state_functionaliy_Negative")
    public void Verify_district_functionaliy_Negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify district code field without selection.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_State_Name_Field(2);
        vcd.click_Update_Button();
        Thread.sleep(3000);
        String errormsg = "Please Select District";
        if ((vcd.district_Error_Message()).equals(errormsg)) {
            test.log(Status.PASS, "district code is not selected in complaint details field");
            logger.info("Test Pass: district code is not selected in complaint details field");
        } else {
            test.log(Status.FAIL, " district code is selected in complaint details field");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: district code is selected in complaint details field");
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




















