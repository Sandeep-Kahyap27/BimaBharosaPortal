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

public class Test_3_SearchComplaintByComplaintNumber extends MainClass {
    Properties properties;
    List<String> lis;

    @Test(priority = 12)
    public void Verify_SearchComplaintByComplaintNumber() throws IOException, InterruptedException {test = extent.createTest("Verify Search Complaint with Complaint Number.");
        test = extent.createTest("Positive: Verify Search Complaint By Complaint Number.");

        lis = register();

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

        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(lis.get(10));
        asp.clickSearchBtn();
        String actualComplaintNo = asp.getFirstRowComplaintFromTable();
        if (lis.get(10).equals(actualComplaintNo)) {
            test.log(Status.PASS, "Searched complaint with complaint number is visible successfully.");
            logger.info("Test Pass: Searched complaint with complaint number is visible successfully.");
        } else {
            test.log(Status.FAIL, "Searched complaint with complaint number is not visible.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched complaint with complaint number is not visible.");
        }


    }
    @Test(priority = 13)
    public void Verify_SearchComplaintByComplaintNumber_complaintDetailsPage() throws IOException, InterruptedException {
        test = extent.createTest("Positive: Verify Search Complaint By Complaint Number Complaint Details Page.");

        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        if (lis.get(10).equals(vcd.visibilityComplaintNumberOnComplaintDetailsPage())) {
            test.log(Status.PASS, "Searched complaint with complaint number is visible on Complaint Details page successfully.");
            logger.info("Test Pass: Searched complaint with complaint number is visible on Complaint Details page successfully.");
        } else {
            test.log(Status.FAIL, "Searched complaint with complaint number is not visible on Complaint Details page.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Searched complaint with complaint number is not visible on Complaint Details page.");
        }
    }
    public List<String> register() throws IOException, InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegTestData");
        String mobile_no = generateRandomMobileNumber();
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
}
