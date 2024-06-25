package ComplaintRegistrationModule;

import Base.MainClass;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import Utilities.DataReader;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class Test_ComplaintRegistration_InsIntermediary extends MainClass {

    @Test(dataProvider = "CompAgainstIntermediary", dataProviderClass = DataReader.class)
    public void test_compRegAgainstInsIntermediary(String name, String mob, String pinCode, String compAgainst, String intermediaryType, String insCompanyName, String policyType, String compType, String compDescType, String brokerName, String policyNumber, String compDesc) throws InterruptedException {

        loadURL("Url");

        test = extent.createTest("Verify user is able to register complaint against Insurance Intermediary");
        HomePage h = new HomePage(driver);
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

        //Complaint Against Intermediary
        reg.complaint_Against(compAgainst);
        reg.intermediary_Type(intermediaryType);
        reg.insCompanyName(insCompanyName);
        reg.policyType(policyType);
        reg.complaintType(compType);
        reg.complaintDescType(compDescType);

        reg.brokerName(brokerName);

        reg.policyNumberClick();
        reg.enterPolicyNumber(policyNumber);

        reg.enterCompDesc(compDesc);
        reg.registerClick();

        reg.acceptAlertWindow();
        Thread.sleep(3000);
        String Msg = reg.getMessage();
        if(reg.registerComplaint()){
            if(Msg.equals("Complaint Registered Successfully.")){
                test.log(Status.PASS,"Complaint Registered Against Insurance Intermediary.");
                logger.info("Complaint Registered Against Insurance Intermediary.");
            }
            else{
                test.log(Status.FAIL, "Complaint not registered Against Insurance Intermediary.");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Complaint not registered Against Insurance Intermediary.");
            }
        }
        else{
            if(Msg.equals("Complaint has been already registered with same policy number and same complaint description")){
                test.log(Status.PASS, "Complaint not registering with duplicate policy number and complaint description");
                logger.info("Complaint not registering with duplicate policy number and complaint description");
            }
            else{
                test.log(Status.FAIL, "Duplicate complaint registered");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Duplicate complaint registered");
            }

        }

    }
}
