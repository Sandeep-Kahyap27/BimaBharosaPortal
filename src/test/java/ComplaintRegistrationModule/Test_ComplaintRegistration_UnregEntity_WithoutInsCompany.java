package ComplaintRegistrationModule;

import Base.MainClass;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import Utilities.DataReader;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class Test_ComplaintRegistration_UnregEntity_WithoutInsCompany extends MainClass {

    @Test(dataProvider = "CompAgainstUnRegWithoutInsCompany", dataProviderClass = DataReader.class)
    public void test_compRegAgainstUnregisteredEntity_WithoutInsCompany(String name, String mob, String pinCode, String compAgainst, String unRegCompName, String unRegCompaddress, String unRegCompPhnNumber, String unRegCompDesc, String unRegComSvcType, String unRegCname, String policyNumber, String compDesc) throws InterruptedException {
        
        loadURL("Url");

        test = extent.createTest("Positive: Verify user is able to register complaint against unregistered entity without Insurance company");
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

        //Complaint against Unregistered entity
        reg.complaint_Against(compAgainst);

        //accept the pop to confirm Insurance Company involvement
        reg.dismissAlertWindow();

        //Unregistered entity details
        reg.enterUnRegCompanyName(unRegCompName);
        reg.enterUnRegCompanyAddress(unRegCompaddress);
        reg.enterUnRegCompanyPhoneNumber(unRegCompPhnNumber);
        reg.enterUnRegCompanyDesc(unRegCompDesc);
        reg.enterTypesOfSvcObtained(unRegComSvcType);
        reg.enterContactPersonName(unRegCname);

        reg.policyNumberClick();
        reg.enterPolicyNumber(policyNumber);

        reg.enterCompDesc(compDesc);
        reg.registerClick();

        reg.acceptAlertWindow();
        Thread.sleep(3000);
        String Msg = reg.getMessage();
        if(reg.registerComplaint()){
            if(Msg.equals("Complaint Registered Successfully.")){
                test.log(Status.PASS,"Complaint Registered Against Unregistered Entity without Insurance Company Involvement.");
                logger.info("Test Passed : Complaint Registered Against Unregistered Entity without Insurance Company Involvement.");
            }
            else{
                test.log(Status.FAIL, "Complaint not registered Against Unregistered Entity without Insurance Company Involvement.");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Failed : Complaint not registered Against Unregistered Entity without Insurance Company Involvement.");
            }
        }
        else{
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
