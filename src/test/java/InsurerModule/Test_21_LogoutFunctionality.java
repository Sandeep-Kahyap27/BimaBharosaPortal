package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.SidebarHeader;
import ObjectRepository.ViewEditComplaintDetailsPage;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_21_LogoutFunctionality extends MainClass {
    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        // launch admin login page
        loadURL("LoginURL");
        //read cred from prop file and login with valid credentials
        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("Hdfc_Ergo_User_Id"), property.getProperty("Hdfc_Ergo_Password"));

    }

    @Test(priority = 89)
    public void Verify_NoBtn_ConfirmPopup() throws InterruptedException {
        test = extent.createTest("Positive: Verify No Button functionality on Logout confirm popup.");
        //Menu-->Complaints-->View/Edit complaints
        SidebarHeader sbr = new SidebarHeader(driver);
        Thread.sleep(1000);
        sbr.clickMenu();
        Thread.sleep(1000);
        sbr.clickcomplaintSidebarTab();
        Thread.sleep(1000);
        sbr.clickviewEditcomplaintSidebarTab();

        Thread.sleep(1000);
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        Thread.sleep(1000);
        vcd.clickOnLogoutBtn();
        if(vcd.click_Update_Record_Cancel()){
            test.log(Status.PASS, "No button on Logout Confirm popup is working properly.");
            logger.info("Test Pass:No button on Logout Confirm popup is working properly.");
        } else {
            test.log(Status.FAIL, "No button on Logout Confirm popup is not working.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:No button on Logout Confirm popup is not working properly.");
        }
    }
    @Test(priority = 90)
    public void Verify_Logout_Functionality() throws InterruptedException {
        test = extent.createTest("Positive: Verify Logout Functionality.");
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        Thread.sleep(1000);
        vcd.clickOnLogoutBtn();
        if(vcd.clickToYesButtonToConfirmRecordUpdateWithReturn()){
            test.log(Status.PASS, "No button on Logout Confirm popup is working properly.");
            logger.info("Test Pass:No button on Logout Confirm popup is working properly.");
        } else {
            test.log(Status.FAIL, "No button on Logout Confirm popup is not working.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:No button on Logout Confirm popup is not working properly.");
       }
    }

}
