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

public class Test_6_DoesNotPertain_NewComplaintStatus extends MainClass {
    List<String> lis;
    @Test(priority = 22)
    public void Verify_DoesNotPertain_without_Remarks_atNewComplaintStatus_Negative() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify Does Not Pertain without Remarks at New Complaint Status.");
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
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Does_Not_Pertain_Button();
        vcd.click_Update_Button();
        String actual_errorMsg = vcd.getErrorMsg_DoesNotPertain_withoutRemarkField();
        String expected_errorMsg = "Please Enter Does Not Pertain";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Validation of error message for Does not pertain without Remarks is successful visible. having complaint no -"+lis.get(10));
            logger.info("Test Pass:Validation of error message for Does not pertain without Remarks is successful visible. having complaint no -"+lis.get(10));
        } else {
            test.log(Status.FAIL, "Validation of error message for Does not pertain without Remarks is not visible. having complaint no -"+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Validation of error message for Does not pertain without Remarks is not visible. having complaint no -"+lis.get(10));
        }
    }

    @Test(priority = 23)
    public void Verify_DoesNotPertain_Remarks_withSpeacialCharacter_atNewComplaintStatus_Negative() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify error message for Does Not Pertain with special Character Remarks at New Complaint Status.");
     //Complaint Details Page
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_Does_Not_Pertain_Field("Test@Remarks");
        String actual_errorMsg = vcd.getErrorMsg_DoesNotPertain_withoutRemarkField();
        String expected_errorMsg = "Please Enter Valid Does Not pertain Remarks";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Validation of error message for Does not pertain with special character Remarks is successful visible. having complaint no -"+lis.get(10));
            logger.info("Test Pass:Validation of error message for Does not pertain with special character Remarks is successful visible. having complaint no -"+lis.get(10));
        } else {
            test.log(Status.FAIL, "Validation of error message for Does not pertain with special character Remarks is not visible. having complaint no -"+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Validation of error message for Does not pertain with special character Remarks is not visible.  having complaint no -"+lis.get(10));
        }
    }

    @Test(priority = 24)
    public void Verify_DoesNotPertain_Remarks_withExceedLimit_atNewComplaintStatus_Negative() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify error message for Does Not Pertain with Exceeding Character Limit os Remarks at New Complaint Status.");
        //Complaint Details Page
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_Does_Not_Pertain_Field(generateRandomString(4005));
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
                break;
            }
        }
        String expected_errorMsg = "You have reached your maximum limit of characters allowed";
        if(actual_errorMsg.equals(expected_errorMsg)){
            test.log(Status.PASS, "Validation of error message for Does not pertain with exceeding character limit of Remarks is successful visible. having complaint no -"+lis.get(10));
            logger.info("Test Pass:Validation of error message for Does not pertain with exceeding character limit of Remarks is successful visible. having complaint no -"+lis.get(10));
        } else {
            test.log(Status.FAIL, "Validation of error message for Does not pertain with exceeding character limit of Remarks is not visible. having complaint no -"+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Validation of error message for Does not pertain with exceeding character limit of Remarks is not visible. having complaint no -"+lis.get(10));
        }

    }

    @Test(priority = 25)
    public void Verify_DoesNotPertain_Remarks_atNewComplaintStatus_Positive() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify  Does Not Pertain with  Remarks at New Complaint Status.");
       //Complaint Details Page
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.enter_Value_Does_Not_Pertain_Field("Test Remarks");
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, "Complaint is updated with Does not pertain is successfully. having complaint no -"+lis.get(10));
            logger.info("Test Pass:Complaint is updated with Does not pertain is successfully. having complaint no -"+lis.get(10));
        } else {
            test.log(Status.FAIL, "Complaint is not updated with Does not pertain. having complaint no -"+lis.get(10));
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail: Complaint is not updated with Does not pertain. having complaint no -"+lis.get(10));
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
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
