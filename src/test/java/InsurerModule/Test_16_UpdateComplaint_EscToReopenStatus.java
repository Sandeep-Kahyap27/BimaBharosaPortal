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

public class Test_16_UpdateComplaint_EscToReopenStatus extends MainClass {
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
    @Test(priority =62)
    public void Verify_UpdateComplaint_EscToReopen_Positive() throws InterruptedException {
        test = extent.createTest("Positive: Verify Update Complaint EscToReopen.");
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        Thread.sleep(2000);
        asp.clickUncheckNewStatus();
        asp.clickUncheckAcknowledgeStatus();
        asp.clickUncheckPendingStatus();
        asp.clickUncheckAttendedToStatus();
        asp.clickEscalatedStatus();
        asp.clickSearchBtn();
        Thread.sleep(4000);
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        String complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        vcd.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcd.click_Complaint_Status_Dropdown("Re-open");
        vcd.click_Update_Button();
        vcd.clickToYesButtonToConfirmRecordUpdate();
        if(vcd.clickToOkButtonConfirmUpdate()){
            test.log(Status.PASS, " Complaint Updated Escalated To Re-open successfully. having complaint no - "+complaintNo);
            logger.info(" Test Pass:Complaint Updated Escalated To Re-open successfully. having complaint no - "+complaintNo);
        } else {
            test.log(Status.FAIL, " Complaint is not Updated Escalated To Re-open. having complaint no - "+complaintNo);
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error(" Test Fail:Complaint is not Updated Escalated To Re-open. having complaint no - "+complaintNo);
        }
    }
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
