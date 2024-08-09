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

public class Test_4_SearchComplaintByClosureRequest extends MainClass {
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
    @Test(priority = 14)
    public void Verify_SearchComplaintBy_Closure_RequestToIRDAI_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify Complaint search by Closure Request");
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
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Attended to");
        crg.scrollToComplaint();
        vcd.enterFinalResolutionRemarks("Test Data");
        vcd.enterFinalResolutionRemarkDateField();
        vcd.click_iF_final_resolution_letter_button();
        vcd.click_upload_attachment_field(System.getProperty("user.dir")+"/src/main/resources/TestData/dummy-pdf_2.pdf");
        vcd.click_Upload_Button();
        vcd.clickToOkButtonConfirmUpdate();
        vcd.clickOnClosureRequestToIRDAI_checkbox();

        vcd.enterDateOfPayment("");
        vcd.enterPaymentChequeDate("");
        vcd.enterInsurersResolutionLetterDate("");
        vcd.enterDateOfHonoringTheService("");
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            AdvanceSearchPage aspAtClosureReq = new AdvanceSearchPage(driver);
            aspAtClosureReq.clickUncheckNewStatus();
            aspAtClosureReq.clickUncheckAcknowledgeStatus();
            aspAtClosureReq.clickUncheckPendingStatus();
            aspAtClosureReq.clickUncheckAttendedToStatus();
            aspAtClosureReq.clickUncheckEscalatedStatus();
            aspAtClosureReq.clickClosureRequestStatus();
            aspAtClosureReq.clickSearchBtn();
            Thread.sleep(2000);
            ViewEditComplaintDetailsPage vcdAtClosureReq = new ViewEditComplaintDetailsPage(driver);
            vcdAtClosureReq.click_View_Edit_Complaints_Checkbox();
            String complaintNo = vcdAtClosureReq.visibilityComplaintNumberOnComplaintDetailsPage();
            if(vcdAtClosureReq.visibilityOfClosureRequestToIRDAI_checkbox()){
                test.log(Status.PASS, "Complaint is searched by Closure Request Successfully. having complaint No - " +complaintNo);
                logger.info("Test Pass: Complaint is searched by Closure Request Successfully. having complaint No - " +complaintNo);
            } else {
                test.log(Status.FAIL, "Complaint is not searched by Closure Request Successfully. having complaint No - " +complaintNo);
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Fail: Complaint is not searched by Closure Request Successfully. having complaint No - " +complaintNo);
            }
        }
    }

}
