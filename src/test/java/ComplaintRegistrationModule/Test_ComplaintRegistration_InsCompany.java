package ComplaintRegistrationModule;

import Base.MainClass;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import Utilities.DataReader;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.util.Random;


public class Test_ComplaintRegistration_InsCompany extends MainClass {


    @Test(dataProvider = "CompAgainstInsComapny", dataProviderClass = DataReader.class)
    public void test_compRegAgainstInsCompany(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws Exception {

        String policy = "289900895678";

        loadURL("Url");

        HomePage h = new HomePage(driver);
        h.OkPopup();
        h.registration();

        ComplaintRegPage reg = new ComplaintRegPage(driver);
        reg.enterName(name);
        reg.enterMobileNumber(mob);
        reg.emailText();

        //Adding wait of 3 seconds to available OTP window
        Thread.sleep(3000);

        h.enterOTP(mob);

        reg.enterPinCode(pinCode);
        reg.scrollToComplaint();

        reg.complaint_Against(compAgainst);
        reg.insCompanyName(insCompanyName);
        //reg.scrollToComplaint();

        reg.policyType(policyType);
        reg.complaintType(compType);
        reg.complaintDescType(compDescType);

        reg.policyNumberClick();
        reg.enterPolicyNumber(policy);

        reg.enterCompDesc(compDesc);
        reg.registerClick();

        reg.acceptAlertWindow();
        Thread.sleep(3000);
        String Msg = reg.getMessage();
        if(reg.registerComplaint()){
            test = extent.createTest("Positive: Verify user is able to register complaint against Insurance company");
            if(Msg.equals("Complaint Registered Successfully.")){
                test.log(Status.PASS,"Complaint Registered Against Insurance company Successfully.");
                logger.info("Test Passed : Complaint Registered Against Insurance company Successfully.");
            }
            else{
                test.log(Status.FAIL, "Complaint not registered");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Failed : Complaint not registered");
            }

        }
        else{
            test = extent.createTest("Verify user is not able to register complaint with duplicate policy number and complaint details");
            if(Msg.equals("Complaint has been already registered with same policy number and same complaint description")){
                test.log(Status.PASS, "Complaint not registering with duplicate policy number and complaint description");
                logger.info("Test Passed : Complaint not registering with duplicate policy number and complaint description");
            }
            else{
                test.log(Status.FAIL, "Duplicate complaint registered");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Failed : Duplicate complaint registered");
            }


        }


    }

}
