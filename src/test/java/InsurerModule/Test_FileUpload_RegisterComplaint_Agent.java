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

public class Test_FileUpload_RegisterComplaint_Agent extends MainClass {
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
    public void test_fileUpload_regComplaint_Agent() throws IOException {
        test = extent.createTest("Positive : Verify Complaint registered against Agent through file upload");

        String entityRefNumber = generateEntityReferenceNo();

        //Setup data to excel file
        ExcelHelper e = new ExcelHelper();
        e.setEntityShortCode(ExcelFileConstants.NEW_NONLIFE, EntityShortCodeConstants.HDFCG);
        e.setGeneratedDateTime(ExcelFileConstants.NEW_NONLIFE, getCurrentDate());

        e.setFirstName(ExcelFileConstants.NEW_NONLIFE, getRandomName(), 4);
        e.setMobileNumber(ExcelFileConstants.NEW_NONLIFE, generateRandomMobileNumber(), 4);
        e.setEntityCompRefNumber(ExcelFileConstants.NEW_NONLIFE, entityRefNumber, 4);
        e.setComplaintDate(ExcelFileConstants.NEW_NONLIFE, getCurrentDate(), 4);
        e.setComplaintReceiptDate(ExcelFileConstants.NEW_NONLIFE, getCurrentDate(), 4);
        e.setPolicyProposalCertClaimNum(ExcelFileConstants.NEW_NONLIFE, generatePolicyNumber(), 4);

        //Register complaint against Agent
        e.setComplaintAgainstTypeId(ExcelFileConstants.NEW_NONLIFE, ComplaintAgainstTypeConstants.AGENT, 4);
        e.setComplaintDetails(ExcelFileConstants.NEW_NONLIFE, "Register complaint against Agent through file upload", 4);

        //Set intermediary name and license number
        e.setIntermediaryName(ExcelFileConstants.NEW_NONLIFE, "ASHOK KUMAR SINGH", 4);
        e.setIntermediaryLicenseNumber(ExcelFileConstants.NEW_NONLIFE, "IRDA/IND/SLA-10181", 4);

        //Register complaint through file upload
        UploadComplaintsPage up = new UploadComplaintsPage(driver);
        up.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.NEW_NONLIFE);
        up.clkUploadBtn();

        //Validation
        if (up.verifySuccessMsg()) {
            test.log(Status.PASS, "Complaint registered against Agent through file upload");
            logger.info("Test Passed : Complaint registered against Agent through file upload");
        } else {
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
