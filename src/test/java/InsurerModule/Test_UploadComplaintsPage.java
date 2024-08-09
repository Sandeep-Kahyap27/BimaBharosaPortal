package InsurerModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.LoginPage;
import ObjectRepository.SidebarHeader;
import ObjectRepository.UploadComplaintsPage;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_UploadComplaintsPage extends MainClass {

    @BeforeClass
    public void beforeMethod() throws IOException, InterruptedException {
        loadURL("LoginURL");
        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(Property.getHDFCErgoUserID(), Property.getHDFCErgoPassword());
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        Thread.sleep(1000);
        sb.clickOnComplaints();
        Thread.sleep(1000);
        sb.clickUploadComplaintsSidebarTab();
    }

    //Author : Abhishek
    @Test
    public void test_uploadComplaints_URL() throws IOException, InterruptedException {
        test = extent.createTest("Positive : Verify upload complaints page URL");

        Properties pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/config.properties");
        String expectedPageURL = pro.getProperty("UploadComplaintsURL");
        String actualPageURL = driver.getCurrentUrl();

        if(expectedPageURL.equals(actualPageURL)){
            test.log(Status.PASS, "Upload complaints page URL is as expected");
            logger.info("Test Passed : Upload complaints page URL is as expected");
        }
        else{
            test.log(Status.FAIL, "URL is incorrect!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : URL is incorrect!");
        }
    }


    //Author : Abhishek
    @Test
    public void test_uploadComplaints_title() throws IOException {
        test = extent.createTest("Positive : Verify upload complaints page title");

        String expectedPageTitle = "Upload Complaints | IRDAI";
        String actualPageTitle  = driver.getTitle();

        if(expectedPageTitle.equals(actualPageTitle)){
            test.log(Status.PASS, "Upload complaints page title is as expected");
            logger.info("Test Passed : Upload complaints page title is as expected");
        }
        else{
            test.log(Status.FAIL, "Upload complaint page title incorrect");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Upload complaint page title incorrect");
        }
    }


    //Author : Abhishek
    @Test
    public void test_visibilityOfAllElements_onUploadComplaintsPage(){
        test = extent.createTest("Positive : Verify visibility of all elements on upload complaints page");

        UploadComplaintsPage up = new UploadComplaintsPage(driver);
        if(up.verifyStatusOfUploadBrowse()){
            test.log(Status.PASS, "Upload excel file field displayed on the page");
            logger.info("Test Passed : Upload excel file field displayed on the page");
        }
        else{
            test.log(Status.FAIL, "Upload excel file field not displayed");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Upload excel file field not displayed");
        }

        if(up.verifyStatusOfUploadBtn()){
            test.log(Status.PASS, "Upload button enabled and visible");
            logger.info("Test Passed : Upload button enabled and visible");
        }
        else{
            test.log(Status.FAIL, "Upload button either disable or not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Upload button either disable or not visible");
        }

        if(up.verifyStatusOfClearBtn()){
            test.log(Status.PASS, "Clear button enabled and visible");
            logger.info("Test Passed : Clear button enabled and visible");
        }
        else{
            test.log(Status.FAIL, "Clear button either disable or not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Clear button either disable or not visible");
        }

        if(up.verifyDownloadTemplateBtn()){
            test.log(Status.PASS, "DownloadTemplate button visible and enabled");
            logger.info("Test Passed : DownloadTemplate button visible and enabled");
        }
        else{
            test.log(Status.FAIL, "DownloadTemplate button either disable or not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : DownloadTemplate button either disable or not visible");
        }
    }

    //Author : Abhishek
    @Test
    public void test_clear_button_functionality(){
        test = extent.createTest("Positive : Verify clear button functionality");

        UploadComplaintsPage up = new UploadComplaintsPage(driver);
        up.uploadExcelFile(System.getProperty("user.dir")+"/src/main/resources/TestData/InvalidExcel.xlsx");
        up.clkUploadBtn();
        up.clkClearBtn();

        if(up.verifyNoRowsDisplayed()){
            test.log(Status.PASS, "Clear button functionality working as expected");
            logger.info("Test Passed : Clear button functionality working as expected");
        }
        else{
            test.log(Status.FAIL, "Clear button functionality not working");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Clear button functionality not working");
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }
}
