package TrackingComplaintModule;

import Base.MainClass;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import ObjectRepository.TrackComplaint_Page;
import Utilities.DataReader;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.util.Random;

public class Test_PolicyHolderComplaint_Positive extends MainClass {
    @Test(dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_registerComplaintAndTrack(String name, String mob, String pinCode, String compAgainst, String insCompany, String policyType, String compType, String compDescType, String policyNumber, String compDesc) throws InterruptedException {

        loadURL("Url");

        test = extent.createTest("Verify user is able to register and track complaint");
        //Random rand = new Random();

        //Register complaint against insurance company
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

        reg.complaint_Against(compAgainst);
        reg.insCompanyName(insCompany);
        //reg.scrollToComplaint();

        reg.policyType(policyType);
        reg.complaintType(compType);
        reg.complaintDescType(compDescType);

        reg.policyNumberClick();
        reg.enterPolicyNumber(policyNumber);

        reg.enterCompDesc(compDesc);
        reg.registerClick();

        reg.acceptAlertWindow();
        Thread.sleep(3000);
        String Msg = reg.getMessage();
        if(reg.registerComplaint()){
            if(Msg.equals("Complaint Registered Successfully.")){
                test.log(Status.PASS,"Complaint Registered Against Insurance company Successfully.");
                logger.info("Complaint Registered Against Insurance company Successfully.");
            }
            else{
                test.log(Status.FAIL, "Complaint not registered");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Complaint not registered");
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

        //Tracking complaint
        h.enterMobileNumber(mob);
        h.trackComplaint();
        h.enterOTP(mob);

        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.view_edit_button_click();
        t.clearField_complaint_desc();
        t.enter_ComplaintDesc("This complaint description edited");
        t.update_complaint_desc();
        t.SelectYesBtn_click();
        t.click_ok();

    }
}



