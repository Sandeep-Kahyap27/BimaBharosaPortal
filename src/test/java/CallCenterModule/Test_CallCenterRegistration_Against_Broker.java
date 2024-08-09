package CallCenterModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallcenterRegPage;
import ObjectRepository.LoginPage;
import ObjectRepository.PopUpPage;
import ObjectRepository.SidebarHeader;
import Utilities.DataReader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_CallCenterRegistration_Against_Broker extends MainClass {

    @Test(dataProvider = "Call_Center_Intermediary", dataProviderClass = DataReader.class)
    public void test_callCenter_registration_against_broker(String identifyBy, String mob, String pinCode, String compAgainst, String intermediaryType, String insCompany, String policyType, String complaintType, String complaintDescType, String brokerName, String policyNumber, String complaintDetails, String source) throws IOException, InterruptedException{

        test = extent.createTest("Positive: Verify call center user is able to register complaint against Broker");
        loadURL("LoginURL");

        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");

        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("CallCenter_UserID"), property.getProperty("CallCenter_Password"));

        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        sb.clickOnComplaints();
        sb.clickOnRegisterAgainstEntity();

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.IsComplaintRegistered();
        cr.identifyBy(identifyBy);
        cr.enterMobileNumberOrEmail(mob);
        cr.search();
        cr.enterPinCode(pinCode);
        cr.enterComplaintAgainstType(compAgainst);
        cr.enterIntermediaryType(intermediaryType);
        scrollToComplaint();
        cr.enterInsuranceCompanyName(insCompany);
        cr.enterPolicyType(policyType);
        cr.enterComplaintType(complaintType);
        cr.enterComplaintDescType(complaintDescType);
        cr.enterBrokerName(brokerName);
        cr.selectPolicyNumber();
        cr.enter_Policy_Proposal_Certificate_Claim_Number(policyNumber);
        cr.enterComplaintDetails(complaintDetails);
        cr.enterSourceOfComplaint(source);
        cr.select_Complaint_Date();
        cr.select_Complaint_Receipt_Date();
        cr.clickOnRegister();

        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(3000);

        String msg = pg.getMessage();
        if(msg != null && msg.equals("Complaint Registered Successfully.")){
            test.log(Status.PASS, "Complaint registered Against Broker by Call Center User");
            logger.info("Test Passed :Complaint registered Against Broker by Call Center User");
        }
        else{
            test.log(Status.FAIL, "Their is some issue while registering complaint");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Their is some issue while registering complaint");
        }

    }

    @AfterClass
    public void logOut() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }
}
