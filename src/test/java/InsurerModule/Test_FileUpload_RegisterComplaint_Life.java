package InsurerModule;

import Base.ExcelHelper;
import Base.LoginHelper;
import Base.MainClass;
import Constants.ComplaintAgainstTypeConstants;
import Constants.EntityShortCodeConstants;
import Constants.ExcelFileConstants;
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

public class Test_FileUpload_RegisterComplaint_Life extends MainClass {


    @BeforeClass
    public void login() throws IOException, InterruptedException {
        // launch admin login page
        loadURL("LoginURL");

        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(Property.getSaharaLifeUserId(), Property.getSaharaLifePassword());
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        Thread.sleep(1000);
        sb.clickOnComplaints();
        Thread.sleep(1000);
        sb.clickUploadComplaintsSidebarTab();
    }

    //Author : Abhishek
    @Test
    public void test_fileUpload_regComplaint_Life() throws IOException, InterruptedException {

        test = extent.createTest("Positive : Verify complaint registered against Life Insurance company through file upload");

        String entityRefNumber = generateEntityReferenceNo();

        //Setup data to excel file
        ExcelHelper e = new ExcelHelper();
        e.setEntityShortCode(ExcelFileConstants.NEW_LIFE, EntityShortCodeConstants.SILIC);
        e.setGeneratedDateTime(ExcelFileConstants.NEW_LIFE, getCurrentDate());

        e.setFirstName(ExcelFileConstants.NEW_LIFE, getRandomName(), 4);
        e.setMobileNumber(ExcelFileConstants.NEW_LIFE, generateRandomMobileNumber(), 4);
        e.setEntityCompRefNumber(ExcelFileConstants.NEW_LIFE, entityRefNumber, 4);
        e.setComplaintAgainstTypeId(ExcelFileConstants.NEW_LIFE, ComplaintAgainstTypeConstants.INSURER,4);
        e.setComplaintDate(ExcelFileConstants.NEW_LIFE, getCurrentDate(), 4);
        e.setComplaintReceiptDate(ExcelFileConstants.NEW_LIFE, getCurrentDate(), 4);
        e.setPolicyProposalCertClaimNum(ExcelFileConstants.NEW_LIFE, generatePolicyNumber(), 4);
        e.setComplaintDetails(ExcelFileConstants.NEW_LIFE,"Register complaint against life insurance company through file upload",4);
        e.setIntermediaryName(ExcelFileConstants.NEW_LIFE,"NIL",4);
        e.setIntermediaryLicenseNumber(ExcelFileConstants.NEW_LIFE,"NIL",4);

        //Register complaint through file upload
        UploadComplaintsPage up = new UploadComplaintsPage(driver);
        up.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.NEW_LIFE);
        up.clkUploadBtn();

        //Validation
        if(up.verifySuccessMsg()){
            test.log(Status.PASS, "Complaint registered against Life Insurance company through file upload");
            logger.info("Test Passed : Complaint registered against Life Insurance company through file upload");
        }
        else{
            test.log(Status.FAIL, "Their is some issue while registering complaint through file upload");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Their is some issue while registering complaint through file upload");
        }
    }


    @AfterClass
    public void logOut() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }

}
