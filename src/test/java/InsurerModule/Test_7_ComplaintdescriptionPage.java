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

public class Test_7_ComplaintdescriptionPage extends MainClass {

    List<String> ls;

    @Test(priority = 29)

    public void Verify_clear_functionality_positive() throws IOException, InterruptedException {
        ls= register();
        test = extent.createTest("Positive: Verify clear button functionality");
        loadURL("LoginURL");

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
        //Advance search with complaint no
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.clickNewStatus();
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        scrollToComplaint();
        vcd.enter_Value_Remark_by_Regulated_Entity_Field("I have issue");
        scrollToComplaint();
        vcd.click_Clear_Button();
        scrollToUpward();
        scrollToUpward();
        scrollToUpward();
        if (vcd.verify_clear_button_functionality()) {
            test.log(Status.PASS, "clear button functionality working as expected");
            logger.info("Test Pass: clear button functionality working as expected");
        } else {
            test.log(Status.FAIL, "clear button functionality not working as expected");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail : clear button functionality not working as expected");
        }
    }

    @Test(priority = 30, dependsOnMethods = "Verify_clear_functionality_positive")
    public void Verify_cancel_functionality_positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify cancel button functionality.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        scrollToComplaint();
        vcd.click_Cancel_Button();
        String expectedurl = "https://igmsuat.irda.gov.in/ViewUpdate/ViewEditComplaint";
        String actualurl = driver.getCurrentUrl();
        if (actualurl.equals(expectedurl)) {
            test.log(Status.PASS, "cancel button functionality is working as expected");
            logger.info("Test pass:cancel button functionality is working as expected");
        } else {
            test.log(Status.FAIL, "cancel button functionality is not working as expected");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:cancel button functionality is not working as expected");
        }
    }

    @Test(priority = 31,dependsOnMethods = "Verify_cancel_functionality_positive")
    public void Verify_senior_citizen_button_functionality_positive() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Senior Citizen Button Functionality");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(" 06-24-000782");
        asp.clickSearchBtn();

        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        vcd.enter_Value_Branch_Code_field(2);
        vcd.enter_Value_State_Name_Field(2);
        vcd.enter_Value_District_Name_Field("guntur");

        vcd.click_senior_citizen_button();
        scrollToComplaint();
        vcd.click_Update_Button();
        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(3000);
        String expectedMessage = "Complaint Updated Successfully";

        if (vcd.get_success_message().equals(expectedMessage)) {
            test.log(Status.PASS, "senior citizen button working as expected");
            logger.info("Test Pass : senior citizen button working as expected");
        } else {
            test.log(Status.FAIL, "senior citizen button not working as expected");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:senior citizen button working as expected");
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


















