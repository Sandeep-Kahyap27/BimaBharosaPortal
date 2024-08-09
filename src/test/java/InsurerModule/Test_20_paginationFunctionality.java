package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallcenterRegPage;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.SidebarHeader;
import ObjectRepository.ViewEditComplaintDetailsPage;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_20_paginationFunctionality extends MainClass {
    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("Hdfc_Ergo_User_Id"), property.getProperty("Hdfc_Ergo_Password"));

    }
    @Test(priority = 87)
    public void Verify_nextBtn_Functionality() throws InterruptedException {
        test = extent.createTest("Positive: Verify next Button functionality of Pagination.");
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
        ComplaintRegPage reg = new ComplaintRegPage(driver);
        reg.scrollToComplaint();
        Thread.sleep(1000);
        if(vcd.clickOnPagination_nextBtn()){
            test.log(Status.PASS, "Pagination Next button is working properly.");
            logger.info("Test Pass:Pagination Next button is working properly.");
        } else {
            test.log(Status.FAIL, "Pagination Next button is not working.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Pagination Next button is not working.");
        }
    }
    @Test(priority = 88)
    public void Verify_previousBtn_Functionality() throws InterruptedException {
        test = extent.createTest("Positive: Verify previous Button functionality of Pagination.");
        Thread.sleep(1000);
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        ComplaintRegPage reg = new ComplaintRegPage(driver);
        reg.scrollToComplaint();
        Thread.sleep(1000);
        if(vcd.clickOnPagination_previousBtn()){
            test.log(Status.PASS, "Pagination previous button is working properly.");
            logger.info("Test Pass:Pagination previous button is working properly.");
        } else {
            test.log(Status.FAIL, "Pagination previous button is not working.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Fail:Pagination previous button is not working.");
        }
    }
    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }
}
